const express = require('express');
const path = require('path');
const jsonServer = require("json-server");

const app = express();
const port = process.env.PORT || 3000;

// Serve static files from the 'public' directory
app.use(express.static(path.join(__dirname, 'public')));

// Use JSON Server to handle API routes
const router = jsonServer.router('db.json');
const server = jsonServer.create();
const middlewares = jsonServer.defaults();

app.use(server, middlewares, router);

// Start the server
app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
});
