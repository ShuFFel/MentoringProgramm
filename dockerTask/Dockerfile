FROM tomcat

RUN apt-get -yqq update
RUN apt-get -yqq install nginx-full


COPY conf/default /etc/nginx/sites-available/default
COPY html/ /usr/share/nginx/html
COPY warApp/hello-world.war /usr/local/tomcat/webapps
COPY mysqript.sh /usr/local/myscripts/mysqript.sh

EXPOSE 80 8080

CMD ["/bin/bash", "/usr/local/myscripts/mysqript.sh"]
