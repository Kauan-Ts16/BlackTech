const connection = require('../database/config');


const getAll = async () => {

    var sql = `SELECT * FROM tbUsuario`;
    const [users] = await connection.execute(sql);

    return users;

};

const getOne = async (id) => {

    var sql = `SELECT * FROM tbUsuario WHERE idUsuario = ?`;
    const [user] = await connection.execute(sql, [id]);

    return user;
}

const save = async (user) => {

    const { nome, email, senha } = user;

    var sql = `INSERT INTO tbUsuario(nome, email, senha) VALUES (?, ?, ?)`;

    const [createdUser] = await connection.execute(sql, [nome, email, senha]);

    return { insertId: createdUser.insertId};

}

const update = async (id, user) => {

    var sql = `UPDATE tbUsuario SET nome = ?, email = ?, senha = ? WHERE idUsuario = ?`;

    const { nome, email, senha } = user;

    const [updatedUser] = await connection.execute(sql, [nome, email, senha, id]);

    return updatedUser;

}

const del = async (id) => {

    var sql = `DELETE FROM tbUsuario WHERE idUsuario = ?`;

    const removeUser = await connection.execute(sql, [id]);

    return removeUser;
}


module.exports = {
    getAll,
    getOne,
    save,
    update,
    del
}