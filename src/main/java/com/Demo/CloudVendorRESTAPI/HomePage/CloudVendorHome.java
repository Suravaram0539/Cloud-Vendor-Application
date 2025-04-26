package com.Demo.CloudVendorRESTAPI.HomePage;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CloudVendorHome {
    public String homePage() {
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Cloud Vendor</title>
            <style>
                html, body {
                    margin: 0;
                    padding: 0;
                    height: 100%;
                    width: 100%;
                    background: #87ceeb; /* Sky blue */
                    overflow: hidden;
                    font-family: Arial, sans-serif;
                }

                .cloud {
                    position: absolute;
                    background: #fff;
                    border-radius: 100px;
                    width: 200px;
                    height: 60px;
                    animation: moveClouds 60s linear infinite;
                }

                .cloud::before,
                .cloud::after {
                    content: '';
                    position: absolute;
                    background: #fff;
                    border-radius: 50%;
                }

                .cloud::before {
                    width: 100px;
                    height: 100px;
                    top: -20px;
                    left: 10px;
                }

                .cloud::after {
                    width: 120px;
                    height: 120px;
                    top: -40px;
                    left: 60px;
                }

                @keyframes moveClouds {
                    0% { left: -250px; }
                    100% { left: 100%; }
                }

                .cloud1 { top: 10%; animation-duration: 40s; animation-delay: 0s; }
                .cloud2 { top: 30%; animation-duration: 50s; animation-delay: 10s; }
                .cloud3 { top: 50%; animation-duration: 60s; animation-delay: 20s; }
                .cloud4 { top: 70%; animation-duration: 70s; animation-delay: 30s; }
                .cloud5 { top: 85%; animation-duration: 80s; animation-delay: 40s; }

                h1 {
                    position: absolute;
                    top: 20px;
                    width: 100%;
                    text-align: center;
                    color: white;
                    text-shadow: 2px 2px 6px #333;
                    font-size: 3rem;
                    z-index: 10;
                }

                .links {
                    position: absolute;
                    top: 50%;
                    left: 50%;
                    transform: translate(-50%, -50%);
                    text-align: center;
                }

                .links a, .links form {
                    display: block;
                    margin: 10px 0;
                }

                .links a {
                    padding: 10px 20px;
                    background: #fff;
                    color: #333;
                    text-decoration: none;
                    border-radius: 5px;
                    box-shadow: 2px 2px 6px rgba(0, 0, 0, 0.2);
                    font-size: 1.2rem;
                }

                .links a:hover {
                    background: #f0f0f0;
                }

                .links form input[type="text"] {
                    padding: 10px;
                    font-size: 1rem;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                    width: 200px;
                }

                .links form input[type="submit"] {
                    padding: 10px 20px;
                    font-size: 1rem;
                    background: #fff;
                    color: #333;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                    cursor: pointer;
                    box-shadow: 2px 2px 6px rgba(0, 0, 0, 0.2);
                }

                .links form input[type="submit"]:hover {
                    background: #f0f0f0;
                }
            </style>
        </head>
        <body>
            <h1>☁️ Welcome to Cloud Vendor ☁️</h1>
            <div class="cloud cloud1"></div>
            <div class="cloud cloud2"></div>
            <div class="cloud cloud3"></div>
            <div class="cloud cloud4"></div>
            <div class="cloud cloud5"></div>
            <div class="links">
                <a href="/cloudvendor/all" target="_blank">Get All Cloud Vendors</a>
                <form onsubmit="event.preventDefault(); redirectToVendorId();">
                <input type="submit" value="Get Cloud Vendor by ID" />
                
                                   <input type="text" id="vendorId" placeholder="Enter Vendor ID" required />
                               </form>
                               <script>
                                   function redirectToVendorId() {
                                       const vendorId = document.getElementById('vendorId').value;
                                       window.location.href = `/cloudvendor/${vendorId}`;
                                   }
                               </script>
                               <h2>(POST,PUT and DELETE requests you can check in Postman)<h2>
            </div>
        </body>
        </html>
        """;
    }
}

//<a href="/cloudvendor" target="_blank">Create Cloud Vendor (POST)</a>
//                <a href="/cloudvendor" target="_blank">Update Cloud Vendor (PUT)</a>
//                <a href="/cloudvendor/delete/{vendorId}" target="_blank">Delete Cloud Vendor</a>