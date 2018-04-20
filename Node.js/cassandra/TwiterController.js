var express = require('express');
var router = express.Router();
var bodyParser = require('body-parser');
router.use(bodyParser.urlencoded({ extended: true }));

// obtem resultado da query 1 para o caminho /query1
router.get('/query1', function (req, res) {

    const cassandra = require('cassandra-driver');
    const client = new cassandra.Client({ contactPoints: ['localhost'], keyspace: 'demo' });

    const query = "SELECT * FROM query1;";
    console.log("query: " + query);

    client.connect()
          .then(function () {
              return client.execute(query);
          })
          .then(function (result) {
              const row = result.rows;
              console.log('Linha: ', row);
              console.log('Desligando');
              res.status(200).send(row);
          })
          .catch(function (err) {
               console.error('Erro ao conectar ', err);
               return client.shutdown();
          });
});

// obtem resultado da query 2 para o caminho /query2
router.get('/query2', function (req, res) {

    const cassandra = require('cassandra-driver');
    const client = new cassandra.Client({ contactPoints: ['localhost'], keyspace: 'demo' });

    const query = "SELECT * FROM query2;";
    console.log("query: " + query);

    client.connect()
          .then(function () {
              return client.execute(query);
          })
          .then(function (result) {
              const row = result.rows;
              console.log('Linha: ', row);
              console.log('Desligando');
              res.status(200).send(row);
          })
          .catch(function (err) {
               console.error('Erro ao conectar ', err);
               return client.shutdown();
          });
});

// obtem resultado da query 3 para o caminho /query3
router.get('/query3', function (req, res) {

    const cassandra = require('cassandra-driver');
    const client = new cassandra.Client({ contactPoints: ['localhost'], keyspace: 'demo' });

    const query = "SELECT * FROM query3;";
    console.log("query: " + query);

    client.connect()
          .then(function () {
              return client.execute(query);
          })
          .then(function (result) {
              const row = result.rows;
              console.log('Linha: ', row);
              console.log('Desligando');
              res.status(200).send(row);
          })
          .catch(function (err) {
               console.error('Erro ao conectar ', err);
               return client.shutdown();
          });
});


module.exports = router;