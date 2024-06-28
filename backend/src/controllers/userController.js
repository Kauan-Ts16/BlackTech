const userModel = require('../models/userModel');


const getAll = async (req, res) => {
    
    const users = await userModel.getAll();

    return res.status(200).json(users);
}

const getOne = async (req, res) => {

    const { id } = req.params;

    const user = await userModel.getOne(id);

    return res.status(200).json(user);

}

const save = async (req, res) => {

    const createdUser = await userModel.save(req.body);

    return res.status(201).json(createdUser);

}

const update = async (req, res) => {

    const { id } = req.params;

    await userModel.update(id, req.body);

    return res.status(204).json();

}

const del = async (req, res) => {
    
    const { id } = req.params;

    await userModel.del(id);

    return res.status(204).json();

}


module.exports = {
    getAll,
    getOne,
    save,
    update,
    del
}