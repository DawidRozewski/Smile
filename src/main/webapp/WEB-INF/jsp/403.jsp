<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;

        }

        .hero-image {
            background-image: url("../../resources/images/tooth1.jpg");
            background-color: dimgray;
            height: 950px;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            position: relative;
        }

        .hero-text {
            text-align: center;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: white;
        }
        .back {
            color: white;
        }
    </style>
</head>
<body>
<div class="hero-image">
    <div class="hero-text">
        <h1 style="font-size:50px">403</h1>
        <h3>Access denied :(</h3>
        <a href="/app" class="back">Go back to the homepage</a>
    </div>
</div>
</body>
</html>













