var express = require('express');
var mongoose = require('mongoose');
var port = process.env.MONGODB_PORT_27017_TCP_PORT;
var ip = process.env.MONGODB_PORT_27017_TCP_ADDR;
var connectionString = 'mongodb://'+ip+':'+port+'/local';
console.log('connecting to mongodb on ' + connectionString);
mongoose.connect(connectionString);

var Schema = mongoose.Schema;

var Post = mongoose.model('Post', { content: String });

// Constants
var PORT = 8888;

// App
var app = express();
app.get('/', function (req, res) {
  var post = new Post({ content: 'asdasdasd' });
  post.save();
  var list = "<ol>";
  Post.find( function (err, posts) {
    for (var i = 0; i < posts.length; i++) {
      list += "<li>" + posts[i] + "</li>";
    }
  });
  list += "</ol>";
  res.send(list + '<form method="post" action="/"><input name="content" type="text"/><button type="submit"></form>');
});
app.post('/', function (req,res) {
  res.redirect('/');
});

app.listen(PORT);
console.log('Running on http://localhost:' + PORT);