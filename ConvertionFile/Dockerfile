# Use a base image do MongoDB
FROM mongo:3.8

# Copie o script de inicialização para o diretório raiz
COPY init.sh /init.sh

# Defina a variável de ambiente para habilitar a autenticação do MongoDB
ENV MONGO_INITDB_ENABLE_AUTH=yes

# Defina as variáveis de ambiente para o nome do banco de dados, usuário e senha
#ENV MONGO_INITDB_DATABASE=seu_banco_de_dados
#ENV MONGO_INITDB_ROOT_USERNAME=seu_usuario
#ENV MONGO_INITDB_ROOT_PASSWORD=sua_senha

# Defina o comando de inicialização para executar o script de inicialização
CMD ["./init.sh"]
