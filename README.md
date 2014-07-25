DDP Server
=========

Meteor uses DDP to send collection data between the client and the server.

With this project I try to see if it's possible to run a DDP server in Scala Play which a Meteor client can connect to.


Start by running the Play project:

    cd playddpserver
    ./activator run

Then start the Meteor project in another console:

    cd meteorclient
    meteor

