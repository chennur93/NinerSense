------------------------------------------------------------
			Niner Sense
------------------------------------------------------------

Included Files: 

Apps:
->'CameraApp'(folder)
->'NinerSense'(mainApp)(folder)

->'gpio.py' (Python script to run GPIO operations on Rpi Client)[This was optional as we inplemented a database in Rpi, but included anyway.]

->PHP files 'login.php', 'register.php', 'lightsUpdate.php', 'UpdateSecurity.php' for running php and SQL commands.

->'TCPClient.java' - used to set up a socket connection to the Rpi from the server for data transfer.

->'FileServer.java' - used to receive the uploaded video file from the camera. Implemented this using a socket too. The connection might be slow but the file gets uploaded on to the server. The mobile phone also retains a copy of the video that is uploaded on to the server.