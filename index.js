const express = require('express');
const app = express();

const bodyParser = require('body-parser');
const multer = require('multer');
const fs = require('fs');
const urlencodedParser = bodyParser.urlencoded({ extended: false })


const vision = require('@google-cloud/vision');

const client = new vision.ImageAnnotatorClient();

const fileName = 'test1.png';

client
    .labelDetection(fileName)
    .then(results => {
        const labels = results[0].labelAnnotations;
        console.log("Labels: ");
        labels.forEach(label => console.log(label.description));
    })
    .catch(err => {
        console.log('Error: ', err);
    });



app.get('/', (req, res) => {
    res.send("Hello world.");
})

app.post('/', (req, res) => {
    console.log("post request");
    res.send('Post');
})

app.get('/index.html', (req, res) => {
    res.sendFile(__dirname + "/" + "index.html");
})

app.get('/get_user',  (req, res) => {
    let response = {
        "first_name" : req.query.first_name,
        "last_name"  : req.query.last_name
    }
    console.log(response);
    res.end(JSON.stringify(response));
})

app.post('/post_user', urlencodedParser, (req, res)=> {
    console.log(req);
    let response = {
        "first_name": req.body.first_name,
        "last_name":  req.body.last_name
    }
    console.log(req);
    res.end(JSON.stringify(response));
})

app.use(express.static('public'));
app.use(bodyParser.urlencoded({ extended: false }));
app.use(multer({ dest: '/tmp/'}).array('image'));

app.post('/file_upload', (req, res) => {
    console.log(req.files[0]);
    let des_file = __dirname + "/img/" + req.files[0].originalname;
    let response = undefined;
    fs.readFile(req.files[0].path, function (err, data) {
        fs.writeFile(des_file, data, (err) => {
            if(err) {
                throw err;
            } else {
                response = {
                    message:'File uploaded successfully',
                    filename: req.files[0].originalname
                };
            }
            console.log(response);
            res.end( JSON.stringify(response) );
        });
    });
})

const server = app.listen(8081, () => {
    let host = server.address().address;
    let port = server.address().port;

    console.log("location: %s:%s", host, port);
});