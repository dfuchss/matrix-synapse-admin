# Matrix Synapse Admin
A simple admin UI for [Synapse](https://github.com/matrix-org/synapse).
The UI contains exactly the functions I currently need for the administration of my instance of Synapse.
Especially the complete Admin API is not implemented yet.
The project itself is still WIP.

## HowTo
Currently the admin ui uses access tokens to connect to the matrix server.
To obtain the token you could eighter use the REST API of Synapse or use the access token from your client session.
(e.g. you can reveal the token of your element client in Settings -> Help & About -> Advanced).
After obtaining the token, just set the server information in the client.
