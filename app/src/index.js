var express = require('express');
var mongoose = require('mongoose');
var mongoPort = process.env.MONGODB_PORT_27017_TCP_PORT;
var ip = process.env.MONGODB_PORT_27017_TCP_ADDR;
var PORT = 8888;

// App
var app = express();
app.use(express.bodyParser());

if(mongoPort === undefined || ip === undefined){
  app.get('/', function (req, res) {
    res.send("Yes! We are UP AND RUNNING!! Node! Inside Docker! Inside Vagrant! Inside John Malkovich!");
  });
}

else {
  var connectionString = 'mongodb://'+ip+':'+mongoPort+'/local';
  console.log('connecting to mongodb on ' + connectionString);
  mongoose.connect(connectionString);

  var Schema = mongoose.Schema;

  var Post = mongoose.model('Post', { content: String });

  app.get('/', function (req, res) {

    Post.find( function (err, posts) {
      var list = "<ol>";
      for (var i = 0; i < posts.length; i++) {
        list += "<li>" + posts[i].content + "</li>";
      }
      list += "</ol>";
      var output = list + '<form method="post" action="/"><input name="content" type="text"/><button type="submit"></form>';
      res.send(output);
    });

  });

  app.post('/', function (req, res) {
    var post = new Post({ content: req.body.content });
    post.save();
    res.redirect('/');
  });
}

app.listen(PORT);
console.log('Running on http://localhost:' + PORT);