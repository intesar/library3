Download and Install the following application if your system doesn't have any.

JDK 1.6
http://java.sun.com/javase/downloads/index.jsp

MySQL 5 Server

When you install mysql note your Root password, you will use that in step 2 and step 5 <br />
http://dev.mysql.com/get/Downloads/MySQL-5.1/mysql-essential-5.1.40-win32.msi/from/http://mysql.he.net/

MySQL GUI Tools
http://dev.mysql.com/get/Downloads/MySQLGUITools/mysql-gui-tools-5.0-r17-win32.msi/from/http://mysql.he.net/

Netbeans 6.8
http://download.netbeans.org/netbeans/6.8/beta/start.html?platform=windows&lang=en&option=java

SVN Client
http://svn1clicksetup.tigris.org/files/documents/3106/33794/Svn1ClickSetup-1.3.3.exe


Steps 1. <br />
Checkout code from <br />
https://library3.googlecode.com/svn/trunk/  <br />

username: your gmail ID  <br />
password: http://code.google.com/hosting/settings   <br />


step 2. <br />
Database restore  <br />
Project folder which you checked out earlier contains a lib folder which contain db.sql file. Use this file to restore database using MySQL Administrator

step 3  <br />
Open project in Netbeans  <br />
Click on "Open Project" on Netbeans and navigate to checked out folder and open it

step 4.  <br />
Add libraries to project

right click on "Libraries" folder of your checked out project in Netbeans and click "Add Jar" then select all the jars from project's lib folder

Step 5.  <br />
Change database configuration <br />
Open src\conf\Persistence.xml and src\java\context\ApplicationContext-Acegi.xml to change your username/password for database configuration


Step 6.  <br />
build & Run  <br />
right click on the project and click "Clean Build"  <br />
after build successful right click again and select "Run" this will open a browser.  <br />
start using or developing application.  <br />