const express = require('express');
const app = express();         
const bodyParser = require('body-parser');
const port = 3000; //porta padrão
const mysql = require('mysql');

function execSQLQuery(sqlQry, res){
  const connection = mysql.createConnection({
  host     : 'localhost',
  port     : 3306,
  user     : 'bruna',
  password : 'bruna',
  database : 'tweets'
  });

  connection.query(sqlQry, function(error, results, fields){
      if(error) 
        res.json(error);
      else
        res.json(results);
      connection.end();
      console.log('executou!');
  });
}

//definindo as rotas
const router = express.Router();
router.get('/', (req, res) => res.json({ message: 'Teste do ICCD Funcionando!' }));
app.use('/', router);

router.get('/query1', (req, res) =>{
    execSQLQuery('select distinct ScreenName as usuario, followers as numeroSeguidores from tweets order by followers desc limit 5;', res);
})

router.get('/query2', (req, res) =>{
    execSQLQuery('select hashtag, count(*) as quantidade from tweets where lang = "pt" group by hashtag;', res);
})

router.get('/query3', (req, res) =>{
    execSQLQuery('select substring(createdAt,1,9) as diaHora, count(*) as quantidade from tweets group by substring(createdAt,1,9);', res);
})


//inicia o servidor
app.listen(port);
console.log('API funcionando!');
