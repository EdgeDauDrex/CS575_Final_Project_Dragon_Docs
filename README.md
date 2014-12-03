CS575_Final_Project_Dragon_Docs
===============================

Drexel CS575: Software Design Final Project Fall 2014

This is the final project for CS575: Software Design at Drexel University for the Fall 2014 quarter. It is a document uploading, viewing, and reviewing system called Dragon Docs.

It was built using Maven for dependency management and using the Spring 4 MVC framework. It is a RESTful webservice, and is easily navigable through links provided on each page.

This system was tested on Tomcat7. To use it on Tomcat7, place the .war file in the webapps subdirectory of your Tomcat home directory, startup Tomcat, and (assuming default Tomcat setup) navigate to http://localhost:8080/DragonDocs. From there, it will automatically navigate to the start page.  (If the provided file does not work, use mvn clean install from the directory with the pom file).

As a RESfFul service, it lacks a traditional login system (to maintain statelessness). Instead, it uses (questionably secure) URI based authentication. At the end of the URI put the options "?uid={uid}&auth={auth}" where {uid} stands for your username and {auth} stands for your auth-string. The auth-string is determined as a function of username, password, bio, account creation date, and account update date. When you create your account, your authentication details will be displayed. Upon update, your URI will change to reflect your new auth-string. It is recommended that you write your auth-string down, and that you update your profile in some way frequently (preferably every time you use it) in order to improve security.

It should also be noted that on very rare occasions it might occur that on transition the system replaces the {uid} with an incorrect value. The fix to this is to simply replace the incorrect value with your username to re-authenticate. This is not a serious error, only a minor inconvenience. DO NOT PANIC if you are suddenly no longer authenticated!  This is a rare incident which occurred in testing that should now be resolved (latest bug fix).

This system allows many types of operations. It is best to watch the youtube video to see the demonstration. Operations include: creating a user, editing a user profile, creating a project, editing a project, uploading documents as pages (character types such as .txt only - no binaries or formated documents such as .docx), and submitting reviews. Projects can be given one of three formats: publicly viewable & reviewable (by Guest), publicly viewable but not publicly reviewable (Guest can view but not review), and viewable by invitation only (private document - do not invite Guest or you will make it publicly viewable). To perform any operation which associates with a user, you must be authenticated as that user.  When editing a project, the summary can be changed and users can be added - either to contribute (which also adds them as viewwers by necessity) or just to view (only matters in view by invitation).

The system has a set of preset categories. At the top level (content resource) it has Fun (id 1) and Work (id 2). Fun has category resources of Walkthroughs (1) Fanfics (2) Original Fiction (3) Poems (4) Scripts (5) and Recipes (6). Work has category resources of CVs (1) Papers (2) Code (3) and Articles (4). Changing these requires only minor adjustments to source code and .json files.

The system allows for creating an arbitrary number of backups of user folders and uploaded documents. To change storage locations require only a minor adjustment to source code. However, it does not have an automatic recovery mechanism. Neither does it automatically backup the .json files. Therefore, such operations must be performed manually by the system administrator.

The system does not allow for updating or removing pages and reviews. It is recommended not to post any content which you will regret having posted, and if you wish to version it should be done either with file names or summaries. However, if you must update a document/review it can be done by the system administrator. The system administrator can delete either by removing entrys from the .json files or by replacing them with dummy entries.

Similarly, the system does not allow for deleting users or projects or changing the names of users or projects. It also does not allow for removing users from projects. It also does not allow editing of content types (work and fun) and of content categories. These operations require manual editing by the system administrator.

The system's performance under the conditions of multiple people tring to modify resources at once is untested. For this reason, it is best to interact in a structured way. 

Taking into account the deficiencies listed above, this system should be considered a skeletal system not suitable for open web deployment yet. However, it could be used internally or developed further into a web suitable system.
