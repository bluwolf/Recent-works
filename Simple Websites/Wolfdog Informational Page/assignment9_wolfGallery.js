var image1 = {
    source: '1.jpg',
    caption:'white wolf lying in the sun',
    tags: ['grasses','white','sun', 'longhair','staring'],
    
};
var image2 = {
    source: '2.jpg',
    caption:'brown wolf looking',
    tags: ['mountain','snow','brown', 'cub','staring'],
    
};
var image3 = {
    source: '3.jpg',
    caption:'two cubs looking towards camera',
    tags: ['grasses','many','rock', 'cub','staring'],
    
};
var image4 = {
    source: '4.jpg',
    caption:'timber wolf looking side ways',
    tags: ['timber','snow','sun', 'grown','looking away'],
    
};
var image5 = {
    source: '5.jpg',
    caption:'timber wolf jogging',
    tags: ['rock','timber','sun', 'short hair','staring'],
    
};
var image6 = {
    source: '6.jpg',
    caption:'timber wolf standing on rock',
    tags: ['rock','timber','wood', 'grown','looking away'],
    
};
var image7 = {
    source: '7.jpg',
    caption:'black wolf staring',
    tags: ['wood','black','snow', 'short hair','staring'],
    
};
var image8 = {
    source: '8.jpg',
    caption:'wolf pack family picture',
    tags: ['wood','white','snow', 'many','staring'],
    
};
var image9 = {
    source: '9.jpg',
    caption:'two cubs lying in the sun',
    tags: ['grasses','many','sun', 'cub','resting'],
    
};
var image10 = {
    source: '10.jpg',
    caption:'white wolf showing head',
    tags: ['terrain','white','rock', 'short hair','staring'],
    
};



var imageArray= new Array ;
imageArray[0]=image1;
imageArray[1]=image2;
imageArray[2]=image3;
imageArray[3]=image4;
imageArray[4]=image5;
imageArray[5]=image6;
imageArray[6]=image7;
imageArray[7]=image8;
imageArray[8]=image9;
imageArray[9]=image10;



 function showImages(){

     
     var newhtml = "";
        for (i=0; i<imageArray.length; i=i+1){
             newhtml = newhtml + "  <img "+" border='3 ' "+" align='left ' "+" height='180px ' " +" width='250px ' "+"src=' " + imageArray[i].source+ "'/> </a>";
        }
         document.getElementById('allPictures').innerHTML = newhtml;
 }


function unhighlightAllImages(){
    var newhtml = "";
        for (i=0; i<imageArray.length; i=i+1){
             newhtml = newhtml + "  <img src='" + imageArray[i].source+ "'/> </a>";
        }
         document.getElementById('allPictures').innerHTML = newhtml;
}

function searchAndHighlight(){
    unhighlightAllImages();
    var searchWord = document.getElementById("search-term").value;
//    alert(searchWord);
    
    
        var newhtml = "";
        for (i=0; i<imageArray.length; i=i+1){

            var caption = imageArray[i].caption.toString().toLowerCase();
            var tags = imageArray[i].tags.toString().toLowerCase();
        
            
            
            if(searchWord && (caption.indexOf(searchWord)) != -1 || tags.indexOf(searchWord) != -1 ){
                newhtml = newhtml + "  <img "+"border='15px ' "+"src=' " + imageArray[i].source+ "' /> </a>";
            }
            else{   
              newhtml = newhtml + "  <img src='" + imageArray[i].source+ "'/> </a>";
            }
        }
         document.getElementById('allPictures').innerHTML = newhtml;
}
    