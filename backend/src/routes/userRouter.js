const express = require('express');
const userController = require('../controllers/userController');

const userRouter = express.Router();


userRouter.get('/users', userController.getAll);

userRouter.get('/users/:id', userController.getOne);

userRouter.post('/users', userController.save);

userRouter.put('/users/:id', userController.update);

userRouter.delete('/users/:id', userController.del);

module.exports = userRouter;