# Apache2 + ApacheTomcat task

## Prepare workspace
+ Install and configure apache2
+ Install and configure ApacheTomcat
+ Clone project and deploy it

### Instaling and configuring Tomcat
+ #### Installing
  + You can download archive with tomcat from:
     http://tomcat.apache.org/
     
     after, you should unpack it into chosen folder and start Tomcat using:
     
     `$sudo /path/to/tomcat/bin/startup.sh`
     >After startup, the default web applications included with Tomcat will be
      >available by visiting:
          http://localhost8080/
 
+ #### Configuring 
  + You should enable the AJP connector in the /path/to/tomcat/conf/server.xml file, by changing server.xml like this:
      ```xml
        <Connector port="8009" enableLookups="false" protocol="AJP/1.3" redirectPort="8443"/>
      ```
  + After, restart Tomcat
      ```
      $sudo /path/to/tomcat/bin/sh startup.sh
      $sudo /path/to/tomcat/bin/sh shutdown.sh
      ```

### Installing and configuring apache2
+ #### Installing
   + You can install apache using this commands:
       ```
       $sudo apt-get update
       $sudo apt-get install apache2
       ```
    + Also you can install it by downloading archive from:
        + http://httpd.apache.org/download.cgi

        and following default instructures from:
        + https://httpd.apache.org/docs/2.4/install.html
    + After installing apache2 you should instal Apache 2 connector for the Tomcat Java servlet engine:
      + You can do it, by running this command:
       
       ` $sudo apt-get install ibapache2-mod-jk`
+ #### Configuring
   * Enable mmod_jk module:

    `$ sudo a2enmod jk`

  * Add new virtual host:

    `$ sudo nano /etc/apache2/sites-available/library.com.conf`

     ```xml
     <VirtualHost *:80>

        ServerName lib.eg
        ServerAlias www.lib.eg

        DocumentRoot /var/www/libr

        ErrorLog ${APACHE_LOG_DIR}/error.log
        CustomLog ${APACHE_LOG_DIR}/access.log combined

        JkMount /* ajp13_worker
        JkUnmount /static/* ajp13_worker

     </VirtualHost>
     ```

  * Configure the workers.properties file for libapache2-mod-jk: 

      `$ sudo nano /etc/libapache2-mod-jk/workers.properties`

    * Change the following properties to appropriate paths:

    `workers.tomcat_home` (for eg. workers.tomcat_home=/usr/share/tomcat)

  * Disable default virtual host: 

    `$ sudo a2dissite 000-default.conf`

  * Enable your virtual host: 

    `$ sudo a2ensite library.com.conf`

  * Restart Apache: 

    `$ sudo service apache2 restart`

### Clone project and deploy
 + Clone project by:
   ```
   $git clone https://github.com/ShuFFel/MentoringProgramm.git
   ````
 + Deploying static content to apache2:
    + Move some static content:
    ```
     $ cd /var/www/
	   $ sudo mkdir libr
	   $ sudo sudo cp -a /path/to/cloned_project/MentoringProgramm/Apache_Tomcat/src/main/webapp/static /var/www/libr/
    ```
    + Restart Apache: 

	    `$ sudo service apache2 restart`
 + Build war and deploy it to the Tomcat:
       
       * Adding Tomcat user for using tomcat-manger GUI: 

        `$ sudo nano /path/to/tomcat/conf/tomcat-users.xml`

       * add the following line: 
        ```xml
          <user username="root" password="root" roles="manager-gui"/>
        ```
       * restart Tomcat:

        ```
        $ sudo /path/to/tomcat/bin/shutdown.sh
        $ sudo /path/to/tomcat/bin/startup.sh	
        ```
    * Open the browser and follow the link: 

         http://localhost:8080/manager/

       > user: root
       > password: root

    * In Application Section select undeploy bottom in row with root path "/".

    * In Deploy Section:

       ``` 	
        Deploy directory or WAR file located on server:
        Context Path (required): /
        XML Configuration file URL:
        WAR or Directory URL:  /path/to/cloned_project/MentoringProgramm/Apache_Tomcat/src/target/springData.war

        Deploy
       ```
