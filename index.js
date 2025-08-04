const express = require('express');
const path = require('path');

const app = express();
const PORTA = 3001; // altere para a porta que você deseja usar

// Servir arquivos estáticos da pasta "public"
app.use(express.static(path.join(__dirname, 'public')));

// Rota principal que serve o index.html
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.listen(PORTA, '0.0.0.0', () => {
  console.log(`HTML disponível em http://SEU_IP:${PORTA}`);
});

