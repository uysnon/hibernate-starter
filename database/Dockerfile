FROM postgres:16.1
RUN localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_US.utf8
ENV POSTGRES_PASSWORD pass
ENV POSTGRES_USER postgres
ENV POSTGRES_DB postgres
#docker build -t postgres-image-for-hiberanate database
#docker images
#docker run --name postgres-container-hibernate -p 5432:5432 -d postgres-image-for-hiberanate