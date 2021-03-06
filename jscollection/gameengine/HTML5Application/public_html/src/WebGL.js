/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
"use strict";

var gGL = null;

function initializeGL() {
    var canvas = document.getElementById("GLCanvas");
    gGL = canvas.getContext("webgl") || canvas.getContext("experimental-webgl");
    
    if (gGL !== null) {
        gGL.clearColor(0.0, 0.8, 0.0, 1.0); // set the color to be cleared (rgba)        
    } else {
      document.write("<br><b>WebGL is not supported!</b>");
    }
    console.log("end of function 1", gGL);
}

function clearCanvas() {
    gGL.clear(gGL.COLOR_BUFFER_BIT);    
}

function doGLDraw() {
    initializeGL();
    clearCanvas();
}


