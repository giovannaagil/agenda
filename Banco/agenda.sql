/**
*Agenda de contatos
*@author Giovanna A. Gil
*/

-- Comando para verificar os bancos criados
show databases;

-- Comando para criar um novo banco de dados
create database agenda;

-- Comando para selecionar um banco de dados
use agenda;

-- Comando usado para excluir um banco de dados
drop database nome_do_banco;

-- Comando usado para criar uma tabela
-- int (tipo de dados: nº inteiros)
-- primary key (chave primária - identificador)
-- auto-increment (numeração automática)
-- varchat (50) (tipo de dados String - 50 caracteres)

create table contatos(
id int primary key auto_increment,
nome varchar (50) not null,
fone varchar (15) not null,
email varchar (50)
);

-- Verificar tabelas do banco de dados
show tables;

-- Descrever a tabela
describe contatos;



