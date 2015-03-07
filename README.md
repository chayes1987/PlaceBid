# PlaceBid
This is the place bid service for my FYP. It is a web service written in Java. It uses a 0mq binding.
It is responsible for relaying bid requests from the clients to the main system.

## Project Setup
Requires a server running on localhost:8080.

## License
None

## Setting up StartAuction service on AWS
- Created AWS EC2 Windows instance
- Connected with RDP and .pem keyfile
- Copied Service build to instance
- Configure Windows Firewall to allow port access for 0mq
- Download Glassfish Server
- Deploy Web Service to Server

- Service runs and works as expected

