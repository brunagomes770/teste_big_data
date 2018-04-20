// app.js
var express = require('express');
var app = express();
var TwiterController = require('./controller/TwiterController');

//Chamada API - http://localhost:3000/api/twiter/

app.use('/api/twiter', TwiterController);

module.exports = app;
