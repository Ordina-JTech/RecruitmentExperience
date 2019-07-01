# Backend for the Recruitment Experience project
This is the backend service for the recruitment experience project.

## Before running the application
* Run `mvn clean install`
* Run the database and local mailserver
  * `cd support-install`
  * `docker-compose up -d`
* Make sure [FFmpeg](https://ffmpeg.org/) is available on your system
* Make sure [ImageMagick](https://imagemagick.org/index.php) is available on your system

To test email functionality, open your browser and go to localhost:3000