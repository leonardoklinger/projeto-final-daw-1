# Projeto Final Desenvolvimento de Aplicações para Web I

![image](https://user-images.githubusercontent.com/60077995/131767815-87f33e1b-cd0e-42e3-92d4-9c43d9b85a77.png)


# Banco de Dados Utilizado PostgreSQL

**Link de download**: https://www.postgresql.org/download/
### 1º passo
Baixar o arquivo com o link a cima ! 
![image](https://user-images.githubusercontent.com/60077995/131768811-78e2fe05-2071-40a5-be7b-18c67d8e258d.png)

### 2º passo
Clique 2 vezes no arquivo baixado.
![image](https://user-images.githubusercontent.com/60077995/131769043-fed530fd-e807-44da-a806-26c790058294.png)

### 3º passo
Clique em next para **iniciar a instalação**
![image](https://user-images.githubusercontent.com/60077995/131769207-8059f2b8-5d83-4500-8a85-a1d0b53dbdba.png)

### 4º passo
Informe a pasta de da instalação, depois clique em **next**. 
![image](https://user-images.githubusercontent.com/60077995/131769282-e9bdd0fd-2b10-480f-a7f0-419771efe349.png)

### 5º passo
Informe a senha do seu banco de dados e depois clique em **next**.
![image](https://user-images.githubusercontent.com/60077995/131770579-e30af6b3-2659-4e6b-bbc5-95f28bc114fc.png)

### Pronto
Foi instalado com **sucesso** seu banco de dados.
![image](https://user-images.githubusercontent.com/60077995/131770968-1e848c1e-2030-48b8-a8d8-58e5822b487d.png)

### Acessando interface do pgAdmin
Procure no menu iniciar por **pgAdmin**.
![image](https://user-images.githubusercontent.com/60077995/131771065-f97ca095-3f30-4e6c-ad99-4eaac6e0394f.png)

### Digite sua senha
![image](https://user-images.githubusercontent.com/60077995/131771299-4551a30d-f981-411f-8004-dce3b708cfe1.png)

### Criando as tabelas.
Clique na opção **Tools** e depois na sub-opção **query tool**
![image](https://user-images.githubusercontent.com/60077995/131771483-9092367e-0a11-4b7c-b5c0-5f41ce20ad78.png)

### Executando os códigos
Dentro desse retângulo branco você terá que coloca o seguintes códigos.

![image](https://user-images.githubusercontent.com/60077995/131771648-46cfe274-e250-4657-b1ea-ba6dc27db3fe.png)

<br>
~~~SQL
CREATE TABLE cadastroleilao (
	id serial PRIMARY KEY,
	nome VARCHAR (50) NOT NULL,
	status VARCHAR (11) NOT NULL,
	valor integer NOT NUll,
	data date NOT NULL,
);

CREATE TABLE lances(
	id serial PRIMARY KEY,
	leilaonome VARCHAR (120) NOT NULL,
	nome VARCHAR (300) NOT NULL,
	valor integer NOT NUll,
);
CREATE TABLE login(
	nome VARCHAR (300) NOT NULL,
	usuario VARCHAR (50) NOT NULL,
	senha VARCHAR (50) NOT NULL,
	email VARCHAR (255) NOT NULL,
	tipo VARCHAR (5) NOT NULL,
);

INSERT INTO login (nome, usuario, senha, email, tipo) values ('admin', 'admin', '123', 'admin@gmail.com', 'admin')
~~~

![image](https://user-images.githubusercontent.com/60077995/131774212-8f94624d-3f07-4379-9972-4b8d7c665a55.png)

Créditos de envios de e-mails: [Felipe Neto](https://github.com/FNetoF/Projeto-Mensagem-E-mail)
