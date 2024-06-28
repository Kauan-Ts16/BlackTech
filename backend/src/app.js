const express = require('express');
const cors = require('cors');
const userRouter = require('./routes/userRouter');

const app = express();

app.use(express.json());
app.use(cors());


// ROTE USERS

app.use('/blacktech', userRouter);

module.exports = app;
