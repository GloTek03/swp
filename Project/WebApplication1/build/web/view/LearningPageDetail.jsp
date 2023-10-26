<%-- 
    Document   : test32
    Created on : Sep 25, 2023, 12:19:17 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <style>
            .container-xl{
                width: 100%;
            }
            html, body {
                position: relative;
            }
            .side-bar{
                position: absolute;
                width: 100%;
                background: #f00;
                top: 150px;
                bottom: 150px;
                height: 100%;
            }

            hr {
                height: 1px;
                background-color: #e5e5e5;
                border: none;
            }

            .chapter{
                display: flex;
                flex-direction: column;
                padding-left: 20px;
                padding-right: 20px;
                border-top: 1px solid black;
                border-bottom: 1px solid black;
                height: 80px;
                cursor: pointer;
            }

            .nav-left {
                flex:1;
                overflow: auto;
                scroll-behavior: smooth;
            }
            .nav-left {
                background: white;
                position: absolute;
                right: 0;
                top: 50px;
                bottom: 0;
                width: 25%;
                height: 100%;
                margin-bottom: 10px;



            }

            .lesson{
                margin-bottom: 10px;
            }





            .side-bar {
                position: relative;
                height: 100%;
                top: 20px;
                right: 0;
            }

            .nav-left {
                position: fixed;
                top: 12px;
                right: 0;
                bottom: 0;
                overflow-y: scroll;
                padding-top: 50px;
                padding-bottom: 40px;
            }

            .cName{

                color: black;
                padding-top: 5%;
                font-weight: bold;
                font-size: 15px;
                padding-bottom: 2%;
                margin-right: 5%;
                width: 95%;
            }

            .numLesson {
                padding-bottom: 5%;
            }

            .ttle{
                font-size: 20px;
                color: black;
                font-weight: bold;
                padding-bottom: 12px;
                border-bottom: 1px solid black;
                padding-left: 20px;
                padding-right: 20px;
            }

            .arw{
                width: 15px;
                height: 15px;
            }

            .chapter:hover{
                background-color: #d6d6d4;
            }

            .lIcon{
                width: 30px;
                margin-right: 20px;
            }


            .arw2{
                width: 100%;
                height: 30px;

            }

            .arw3{
                width: 25px;
                height: 25px;
                margin-right: 20px;
            }

            .in4{
                display: flex;
                flex-direction: row;
                align-items: center;
            }

            .lesson{
                display: flex;
                flex-direction: row;
                align-items: center;
                padding-left: 20px;
                font-size: 15px;
                padding-top: 5px;
                padding-bottom: 5px;
                cursor: pointer;
            }

            .quiz{
                display: flex;
                flex-direction: column;


                cursor: pointer;
            }


            .iQuiz {
                display: flex;
                flex-direction: row;
                align-items: center;
                padding-left: 80px;
                font-size: 15px;
                padding-top: 8px;
                margin-bottom: 10px;
            }

            .lessonP{
                padding-top: 10px;
                padding-bottom: 10px;
                display: none;
            }
            .lessonPActive{
                padding-top: 10px;
                padding-bottom: 10px;
            }
            .lName a{
                color: black;
            }

            .msg{
                color: black;
                font-size: 15px;
                padding-left: 25px;
            }

            .material{
                margin-top: 20px;
                margin-right: 20px;
                margin-left: -62px;
                width: 79%;
            }

            .video{
                margin: 0px;

            }

            .video #iframe{
                width: 100%;
                height: 500px;

            }
            .lessonActive{
                display: flex;
                flex-direction: row;
                align-items: center;
                padding-left: 20px;
                font-size: 15px;
                padding-top: 5px;
                padding-bottom: 5px;
                cursor: pointer;
                color: #00c0ef;
            }

            .lessonActive a{
                color: #00c0ef;
            }

            .lttle{
                font-size: 15px;
                color: black;
                font-weight: bold;
                padding-bottom: 12px;
                padding-left: 25px;
                padding-right: 25px;
            }

            .qttle{
                font-size: 15px;
                color: black;
                font-weight: bold;
                padding-bottom: 12px;
                padding-left: 80px;
                padding-right: 25px;
            }

            .lName{
                width: 240px;
                margin-right: 15px;
            }

            .quiz{
                display: none;
            }

            .quizActive{
                display: active;
            }

            .vidTtle{
                margin-top: 15px;
                font-size: 23px;
                font-weight: bold;
                margin-bottom: 10px;
            }

            .visDes{
                margin-top: 10px;
                margin-bottom: 20px;
                font-size: 15px;
                height: 80px;
                width: 90%;
                height: 70px;
                overflow: hidden;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-line-clamp: 4;
                -webkit-box-orient: vertical;
                line-height: 1.6;
            }

            .vidDes{
                margin-top: 10px;
                margin-bottom: 20px;
                font-size: 15px;
                height: 140px;
                width: 90%;
                line-height: 1.6;
            }

            .visDes:hover{
                background-color: #d6d6d4;
                cursor: pointer;
            }

            .tab{
                display: flex;
                flex-direction: row;
                margin-top: 15px;
                font-size: 18px;
                border-bottom: 1px solid grey;

            }

            .tab1{
                width: 50%;
                margin-right: 20px;
                border-bottom: 2px solid black;
                margin-right: 20px;
                font-weight: bold;
            }

            .tab1:hover{
                cursor: pointer;
            }

            .tab2:hover{
                cursor: pointer;
            }

            .tab2{
                width: 50%;
                text-align: right;
            }

            #omtr{
                display: none;
            }

            #irm{
                border: 1px solid black;
            }

            .trainerName{
                font-weight: bold;
                font-size: 18px;
            }
            .control{
                width: 30px;
                margin-right: 0px;

            }
            #iconL{
                position: absolute;
                right: 0;
                top: 50%;
                bottom: 0;
                width: 10%;
                height: 100%;
                margin-bottom: 10px;
                cursor: pointer;
                display: none;
            }
            #iconL img{
                width: 100%;
            }
            .iconR{
                width: 20px;
                margin-right: 0px;
            }
            .ttle:hover{
                cursor: pointer;
            }

            .qname{
                font-size: 30px;
                font-weight: bold;
            }

            .vidDes{
                margin-bottom: 20px;
            }

            .duration{
                margin-top: 40px;
                display: flex;
                flex-direction: row;
                font-size: 20px;

                margin-bottom: 10px;
                align-items: center;
            }

            .i{
                width: 30px;
                margin-right: 15px;
            }

            .text{
                color: red;
            }

            .btn{
                margin-top: 10px;
                border: 1px solid black;
                border-radius: 12px;
                padding-top: 10px;
                padding-bottom: 10px;
                padding-left: 15px;
                padding-right: 15px;
                width: 120px;
                background-color: red;
                color: white;
            }

            .attempt{
                font-size: 20px;
                margin-bottom: 10px;
            }


            /*POP UP*/
            .popup {
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.4);
                display: none;
            }
            .popup-content {
                background-color: white;
                margin: 10% auto;
                padding: 20px;
                border: 1px solid #888888;
                width: 30%;
                font-weight: bolder;
            }
            .popup-content button {
                display: block;
                margin: 0 auto;
            }
            .show {
                display: block;
            }
            h1 {
                color: green;
            }
            .popup-content{
                font-size: 20px;
            }
            p{
                text-align: center;
            }
            .popupBtn{
                display: flex;
                flex-direction: row;
                justify-content: center;
            }

            .popupBtn button{
                width: 80px;
                border: 1px solid black;
                border-radius: 12px;
                padding: 5px;
            }

            .popupBtn button:hover{
                background-color: black;
                color: white;
            }

            .rw{
                border-radius: 12px;
                background-color: #0075b0;
                color:white;
                margin-left: 40px;
            }

            #myButton{
                border-radius: 12px;
                background-color: red;
                color: white;
            }
            
            .ac{
                width: 20px;
            }
        </style>
    </head>
    <body>
        <%@include file = "menu.jsp" %>
        <%@include file = "navbarTrainee2.jsp" %>
        <!--
            Write code
        
        -->
        <aside class="right-side">
            <div class="container   -xl">
                <div class = "material" id = "material">
                    <c:if test = "${requestScope.quiz != null}">
                        <div id="myPopup" class="popup">
                            <div class="popup-content">

                                <p>Are you sure you want to enter this quiz ?</p>
                                <div class = "popupBtn">
                                    <button id="go" onclick = "go()">
                                        OK
                                    </button>
                                    <button id="closePopup">
                                        Close
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class = "qname">${quiz.getQuizName()}</div>
                        <div class = "vidDes">
                            ${quiz.getDescription()}
                        </div>
                        <div class = "duration">
                            <div class = "i">
                                <img src ="img/durationIcon.png"/>
                            </div>
                            <div class = "text">
                                ${quiz.getTimeLimit()} minutes
                            </div>
                        </div>
                        <c:if test = "${requestScope.listQG != null}">
                            <c:forEach var="i" items="${requestScope.listQG}" varStatus="loop">
                                <div class = "attempt">
                                    #${i.getCount()} attempt: ${i.getGrade()} / 10 <a href = "#"><button class ="rw" type = "button">Review</button></a>
                                </div>

                            </c:forEach>
                            <div class = "attempt">
                                Highest attempt: ${requestScope.highest}
                            </div>
                            <button id ="myButton" type ="button" class ="btn">RETAKE</button>
                        </c:if>
                        <c:if test = "${requestScope.listQG == null}">
                            <button id ="myButton" type ="button" class ="btn">TAKE</button>
                        </c:if>
                    </c:if>
                    <div class = "video" id = "video">
                        <input type ="hidden" id ="state" name ="state" value ="${requestScope.state}"/>
                        <input type ="hidden" id ="lesson" name ="lesson" value ="${requestScope.lesson}"/>
                        <input type ="hidden" id ="subject" name ="subject" value ="${requestScope.subject}"/>
                        <input type ="hidden" id ="cl" name ="cl" value ="${requestScope.cl}"/>
                        <c:if test = "${requestScope.video != null}">
                            <input type ="hidden" id ="videoID" value ="${requestScope.videoID}"/>
                            <div id = "irm">

                            </div>

                            <div class = "videoIN4">
                                <div class = "vidTtle">
                                    Subject Introduction
                                </div>
                                <div id = "tab" class = "tab">
                                    <div id = "tab1" class = "tab1" onclick = "tab('tab1')">
                                        Description
                                    </div>
                                    <div id = "tab2" class = "tab2" onclick = "tab('tab2')">
                                        Trainer
                                    </div>
                                </div>
                                <div id = "videoIN4">
                                    <div id ="des" class = "visDes" onclick = "showAll()">
                                        <input type ="hidden" value ="${requestScope.des}" id ="desc"/>
                                        ${requestScope.des}
                                    </div>
                                </div>
                                <div id = "omtr">
                                    <div class = "vidDes">
                                        <div class = "trainerName">
                                            <c:if test = "${requestScope.trainer.getGender() == 'Male'}">
                                                Mr. ${requestScope.trainer.getFullName()}
                                            </c:if>
                                            <c:if test = "${requestScope.trainer.getGender() == 'Female'}">
                                                Ms. ${requestScope.trainer.getFullName()}
                                            </c:if>
                                        </div>
                                        <div>
                                            <div id ="desT" class = "visDes" onclick = "showAllT()">
                                                <input type ="hidden" value ="${requestScope.des}" id ="descT"/>
                                                ${requestScope.trainer.getDescription()}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>                    
                        </c:if>
                    </div>

                </div>
                <div class=”side-bar” id = "side-bar">
                    <div onclick ="right()" id ="iconL">
                        <img class ="iconL" src ="img/leftIcon.png"/>
                    </div>
                    <div class="nav-left" id = "nav-left">

                        <input type ="hidden" value ="${requestScope.chapter}" id ="scroll"/>
                        <div class = "ttle" onclick = "left()">Subject content &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <img class ="iconR" src ="img/rightIcon.png"/>
                        </div>
                        <c:forEach var="i" items="${requestScope.list}" varStatus="loop">
                            <c:if test = "${i.getChapter().getChapterID() == requestScope.chapter}">
                                <div class = "chapter" id = "chapter${i.getChapter().getChapterID()}" onclick ="show('${i.getChapter().getChapterID()}')">
                                    <div class = "in4">
                                        <div class = "cName">Part: ${i.getChapter().getChapterID()} - ${i.getChapter().getChapterName()}</div>
                                        <img id ="img${i.getChapter().getChapterID()}" src ="img/upArrow.png" class = "arw"/>
                                    </div>
                                    <div  class ="numLesson">${i.getChapter().getNumAchieve()} / ${i.getList().size()}</div>
                                </div>
                            </c:if>

                            <c:if test = "${i.getChapter().getChapterID() != requestScope.chapter}">
                                <div class = "chapter" id = "chapter${i.getChapter().getChapterID()}" onclick ="show('${i.getChapter().getChapterID()}')">
                                    <div class = "in4">
                                        <div class = "cName">Part: ${i.getChapter().getChapterID()} - ${i.getChapter().getChapterName()}</div>
                                        <img id ="img${i.getChapter().getChapterID()}" src ="img/downArrow.png" class = "arw"/>
                                    </div>
                                    <c:if test = "${i.getChapter().getNumAchieve() != i.getList().size()}">
                                        <div  class ="numLesson">${i.getChapter().getNumAchieve()} / ${i.getList().size()}</div>
                                    </c:if>
                                    <c:if test = "${i.getChapter().getNumAchieve() == i.getList().size()}">

                                        <div  class ="numLesson">
                                            <img class ="ac" src ="img/activateIcon.png"/>
                                            &nbsp;
                                            ${i.getChapter().getNumAchieve()} / ${i.getList().size()}
                                        </div>
                                    </c:if>
                                </div>
                            </c:if>
                            <c:if test = "${i.getChapter().getChapterID() == requestScope.chapter}">
                                <div class = "lessonPActive" id = "${i.getChapter().getChapterID()}">
                                    <c:forEach var="j" items="${i.getList()}" varStatus="loop">
                                        <c:if test = "${j.getLessonType().getSetting_name() == 'Video'}">
                                            <c:if test = "${j.getLessonID() == requestScope.lesson}">
                                                <div class = "lessonActive">
                                                    <c:if test = "${j.isAchieve() == 'true'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/activateIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <c:if test = "${j.isAchieve() == 'false'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/videoIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <div class = "lName">
                                                        <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()}</a>
                                                    </div>
                                                </div>          
                                            </c:if>
                                            <c:if test = "${j.getLessonID() != requestScope.lesson}">
                                                <div class = "lesson">
                                                    <c:if test = "${j.isAchieve() == 'true'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/activateIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <c:if test = "${j.isAchieve() == 'false'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/videoIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <div class = "lName">
                                                        <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()}</a>
                                                    </div>
                                                </div>          
                                            </c:if>
                                        </c:if>
                                        <c:if test = "${j.getLessonType().getSetting_name() == 'Quiz'}">
                                            <c:if test = "${j.getLessonID() == requestScope.lesson}">
                                                <div class = "lessonActive">
                                                    <c:if test = "${j.isAchieve() == 'true'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/activateIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <c:if test = "${j.isAchieve() == 'false'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/quizIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <div class = "lName">
                                                        <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()}</a>
                                                    </div>
                                                </div>          
                                            </c:if>
                                            <c:if test = "${j.getLessonID() != requestScope.lesson}">
                                                <div class = "lesson">
                                                    <c:if test = "${j.isAchieve() == 'true'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/activateIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <c:if test = "${j.isAchieve() == 'false'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/quizIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <div class = "lName">
                                                        <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()}</a>
                                                    </div>
                                                </div>          
                                            </c:if>
                                        </c:if>
                                        <c:if test = "${j.getLessonType().getSetting_name() == 'Assignment'}">
                                            <c:if test = "${j.getLessonID() == requestScope.lesson}">
                                                <div class = "lessonActive">
                                                    <div class = "lIcon">
                                                        <img src = "img/asmIcon.png" class = "arw2"/>
                                                    </div>
                                                    <div class = "lName">
                                                        <a href = "#">${j.getLessonID()}. ${j.getLessonName()}</a>
                                                    </div>
                                                </div>          
                                            </c:if>
                                            <c:if test = "${j.getLessonID() != requestScope.lesson}">
                                                <div class = "lesson">
                                                    <div class = "lIcon">
                                                        <img src = "img/asmIcon.png" class = "arw2"/>
                                                    </div>
                                                    <div class = "lName">
                                                        <a href = "#">${j.getLessonID()}. ${j.getLessonName()}</a>
                                                    </div>
                                                </div>          
                                            </c:if>
                                        </c:if>
                                    </c:forEach>

                                </div>
                            </c:if>


                            <c:if test = "${i.getChapter().getChapterID() != requestScope.chapter}">
                                <div class = "lessonP" id = "${i.getChapter().getChapterID()}">
                                    <c:forEach var="j" items="${i.getList()}" varStatus="loop">
                                        <c:if test = "${j.getLessonType().getSetting_name() == 'Video'}">
                                            <c:if test = "${j.getLessonID() == requestScope.lesson}">
                                                <div class = "lessonActive">
                                                    <c:if test = "${j.isAchieve() == 'true'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/activateIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <c:if test = "${j.isAchieve() == 'false'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/videoIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <div class = "lName">
                                                        <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()}</a>
                                                    </div>
                                                </div>          
                                            </c:if>
                                            <c:if test = "${j.getLessonID() != requestScope.lesson}">
                                                <div class = "lesson">
                                                    <c:if test = "${j.isAchieve() == 'true'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/activateIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <c:if test = "${j.isAchieve() == 'false'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/videoIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <div class = "lName">
                                                        <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()}</a>
                                                    </div>
                                                </div>          
                                            </c:if>
                                        </c:if>
                                        <c:if test = "${j.getLessonType().getSetting_name() == 'Quiz'}">
                                            <c:if test = "${j.getLessonID() == requestScope.lesson}">
                                                <div class = "lessonActive">
                                                    <c:if test = "${j.isAchieve() == 'true'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/activateIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <c:if test = "${j.isAchieve() == 'false'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/quizIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <div class = "lName">
                                                        <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()}</a>
                                                    </div>
                                                </div>          
                                            </c:if>
                                            <c:if test = "${j.getLessonID() != requestScope.lesson}">
                                                <div class = "lesson">
                                                    <c:if test = "${j.isAchieve() == 'true'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/activateIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <c:if test = "${j.isAchieve() == 'false'}">
                                                        <div class = "lIcon">
                                                            <img src = "img/quizIcon.png" class = "arw2"/>
                                                        </div>
                                                    </c:if>
                                                    <div class = "lName">
                                                        <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()}</a>
                                                    </div>
                                                </div>          
                                            </c:if>
                                        </c:if>
                                        <c:if test = "${j.getLessonType().getSetting_name() == 'Assignment'}">
                                            <c:if test = "${j.getLessonID() == requestScope.lesson}">
                                                <div class = "lessonActive">
                                                    <div class = "lIcon">
                                                        <img src = "img/asmIcon.png" class = "arw2"/>
                                                    </div>
                                                    <div class = "lName">
                                                        <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()}</a>
                                                    </div>
                                                </div>          
                                            </c:if>
                                            <c:if test = "${j.getLessonID() != requestScope.lesson}">
                                                <div class = "lesson">
                                                    <div class = "lIcon">
                                                        <img src = "img/asmIcon.png" class = "arw2"/>
                                                    </div>
                                                    <div class = "lName">
                                                        <a href = "learn?subject=${requestScope.subject}&class=${requestScope.cl}&lesson=${j.getLessonID()}">${j.getLessonID()}. ${j.getLessonName()}</a>
                                                    </div>
                                                </div>          
                                            </c:if>
                                        </c:if>
                                    </c:forEach>

                                </div>
                            </c:if>


                        </c:forEach>

                    </div>

                </div>
                <hr>
            </div>
        </aside>
        <%@include file = "footer.jsp" %>
        <script type ="text/javascript">
            document.getElementById("myButton").addEventListener("click", function () {
            document.getElementById("myPopup").classList.add("show");
            });
            document.getElementById("closePopup").addEventListener("click", function () {
            document.getElementById("myPopup").classList.remove("show");
            });
            window.addEventListener("click", function (event) {
            if (event.target == document.getElementById("myPopup")) {
            document.getElementById("myPopup").classList.remove("show");
            }
            });
        </script>
        <script type="text/javascript">
            var txt = document.getElementById("desc").value;
            var txtT = document.getElementById("descT").value;
            function show(id) {
            var img = document.getElementById("img" + id);
            if (img.src === "http://localhost:9999/prj/img/upArrow.png") {
            document.getElementById(id).style.display = 'none';
            img.src = "http://localhost:9999/prj/img/downArrow.png";
            } else if (img.src === "http://localhost:9999/prj/img/downArrow.png") {
            document.getElementById(id).style.display = 'block';
            img.src = "http://localhost:9999/prj/img/upArrow.png";
            }

            }

            function showL(id) {
            var img = document.getElementById("imgL" + id);
            if (img.src === "http://localhost:9999/prj/img/upArrow.png") {
            document.getElementById("lesson" + id).style.display = 'none';
            img.src = "http://localhost:9999/prj/img/downArrow.png";
            } else if (img.src === "http://localhost:9999/prj/img/downArrow.png") {
            document.getElementById("lesson" + id).style.display = 'block';
            img.src = "http://localhost:9999/prj/img/upArrow.png";
            }
            }

            var a = document.getElementById("scroll").value;
            document.getElementById("chapter" + a).scrollIntoView({behavior: "smooth", inline: "nearest", alignToTop: true});
//            function truncate(elt, content, height) {
//
//                function getHeight(elt) {
//                    return elt.getBoundingClientRect().height;
//                }
//                function shorten(str) {
//                    return str.slice(0, -1);
//                }
//
//                elt.style.height = "auto";
//                elt.textContent = content;
//
//                // Shorten the string until it fits vertically.
//                while (getHeight(elt) > height && content) {
//                    elt.textContent = (content = shorten(content)) + '...thêm';
//                }
//
//            }
            function showAll() {
            document.getElementById("des").classList.remove("visDes");
            document.getElementById("des").classList.add("vidDes");
            document.getElementById("des").textContent = txt;
            document.getElementById("material").style.width = "79%";
            document.getElementById("material").style.marginRight = "20px";
            document.getElementById("material").style.marginLeft = "-54px";
            document.getElementById("side-bar").style.width = "100%";
            document.getElementById("nav-left").style.width = "24%";
            document.getElementById("irm").style.border = "1px solid black";
//                console.log(document.getElementById("des").textContent);

            // Shorten the string until it fits vertically.
            }

            function showAllT() {
            document.getElementById("desT").classList.remove("visDes");
            document.getElementById("desT").classList.add("vidDes");
            document.getElementById("des").textContent = txt;
            document.getElementById("material").style.width = "79%";
            document.getElementById("material").style.marginRight = "20px";
            document.getElementById("material").style.marginLeft = "-54px";
            document.getElementById("side-bar").style.width = "100%";
            document.getElementById("nav-left").style.width = "24%";
            document.getElementById("irm").style.border = "1px solid black";
//                console.log(document.getElementById("des").textContent);

            // Shorten the string until it fits vertically.
            }

            function right() {
            document.getElementById("nav-left").style.display = 'block';
            document.getElementById("material").style.width = '79%';
            document.getElementById("iconL").style.display = 'none';
            }

            function left() {
            document.getElementById("nav-left").style.display = 'none';
            document.getElementById("material").style.width = '100%';
            document.getElementById("iconL").style.display = 'block';
            console.log(document.getElementById("iconL").style.display);
            document.getElementById("iconL").style.width = '20px'
            }

            function tab(id) {
            console.log(id);
            if (String(id) === 'tab1') {
            document.getElementById("videoIN4").style.display = 'block';
            document.getElementById("omtr").style.display = 'none';
            document.getElementById("tab1").style.fontWeight = "bold";
            document.getElementById("tab2").style.fontWeight = "normal";
            document.getElementById("tab1").style.borderBottom = "2px solid black";
            document.getElementById("tab2").style.border = "none";
            console.log(document.getElementById("material").style.width);
            if (String(document.getElementById("material").style.width) === "100%") {
            document.getElementById("material").style.width = "100%";
            document.getElementById("material").style.marginRight = "20px";
            document.getElementById("material").style.marginLeft = "-54px";
            document.getElementById("side-bar").style.width = "100%";
            document.getElementById("nav-left").style.width = "24%";
            document.getElementById("irm").style.border = "1px solid black";
            }
            else {
            document.getElementById("material").style.width = "79%";
            document.getElementById("material").style.marginRight = "20px";
            document.getElementById("material").style.marginLeft = "-54px";
            document.getElementById("side-bar").style.width = "100%";
            document.getElementById("nav-left").style.width = "24%";
            document.getElementById("irm").style.border = "1px solid black";
            }
            } else if (String(id) === 'tab2') {
            document.getElementById("videoIN4").style.display = 'none';
            document.getElementById("omtr").style.display = 'block';
            document.getElementById("tab2").style.fontWeight = "bold";
            document.getElementById("tab1").style.fontWeight = "normal";
            document.getElementById("tab2").style.borderBottom = "2px solid black";
            document.getElementById("tab1").style.border = "none";
            console.log(document.getElementById("material").style.width);
            if (String(document.getElementById("material").style.width) === "100%") {
            document.getElementById("material").style.width = "100%";
            document.getElementById("material").style.marginRight = "20px";
            document.getElementById("material").style.marginLeft = "-54px";
            document.getElementById("side-bar").style.width = "100%";
            document.getElementById("nav-left").style.width = "24%";
            document.getElementById("irm").style.border = "1px solid black";
            }
            else {
            document.getElementById("material").style.width = "79%";
            document.getElementById("material").style.marginRight = "20px";
            document.getElementById("material").style.marginLeft = "-54px";
            document.getElementById("side-bar").style.width = "100%";
            document.getElementById("nav-left").style.width = "24%";
            document.getElementById("irm").style.border = "1px solid black";
            }
            }
            }




//            var a = 0;

//            const y = document.getElementById("chapter" + a).getBoundingClientRect().top + window.scrollY;
//            console.log(y);
//            
//            window.scroll({
//                top: y,
//                behavior: 'smooth'
//            });
//            window.scrollTo(0, y);
        </script>

        <script>
            var io = require("socket.io")(http, {
            cors: {
            origin: "*",
                    methods: ["GET", "POST"]
            }
            });
            fetch(https://localhost:9999/prj/learn.)
                    .then(response => {
                    return response.json();
                    })
                    .then(data => {
                    console.log(data.results);
                    }).catch(error => console.log('Request failed:', error));
        </script>

        <!-- YOUTUBE IFRAME API -->
        <script type = "text/javascript">
            // 2. This code loads the IFrame Player API code asynchronously.
            var tag = document.createElement('script');
            tag.src = "https://www.youtube.com/iframe_api";
            var firstScriptTag = document.getElementsByTagName('script')[0];
            firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
            // 3. This function creates an <iframe> (and YouTube player)
            //    after the API code downloads.
            var player;
            var id = document.getElementById("videoID").value;
            function onYouTubeIframeAPIReady() {
            player = new YT.Player('irm', {
            height: '500px',
                    width: '100%',
                    videoId: id,
                    playerVars: {
                    'playsinline': 1,
                            'origin': 'http://localhost:9999'
                    }

            });
            player.currentTime = document.getElementById("videoID").value;
            // playerSeekTo(player, document.getElementById("state").value);
            var iframeWindow = player.getIframe().contentWindow;
            // So we can compare against new updates.
            var lastTimeUpdate = 0;
            // Listen to events triggered by postMessage,
            // this is how different windows in a browser
            // (such as a popup or iFrame) can communicate.
            // See: https://developer.mozilla.org/en-US/docs/Web/API/Window/postMessage
            window.addEventListener("message", function (event) {
            // Check that the event was sent from the YouTube IFrame.
            if (event.source === iframeWindow) {
            var data = JSON.parse(event.data);
            // The "infoDelivery" event is used by YT to transmit any
            // kind of information change in the player,
            // such as the current time or a playback quality change.
            if (
                    data.event === "infoDelivery" &&
                    data.info &&
                    data.info.currentTime
                    ) {
            // currentTime is emitted very frequently (milliseconds),
            // but we only care about whole second changes.
            //var a = player.getDuration();
            var time = Math.floor(data.info.currentTime);
            if (Number(document.getElementById("state").value) !== 0) {
            if (time == 0) {
            player.seekTo(Number(document.getElementById("state").value), true);
            data.info.currentTime = document.getElementById("state").value;
            player.currentTime = document.getElementById("state").value;
            }
            }
            let http = new XMLHttpRequest();
            console.log(time + " " + document.getElementById("lesson").value);
            if (time % 10 == 0) {
            var url = "http://localhost:9999/prj/test";
            let http = new XMLHttpRequest();
            http.open('POST', url, true);
            http.setRequestHeader("Access-Control-Allow-Origin", "*");
            http.setRequestHeader("Access-Control-Allow-Origin", "POST, GET, PUT");
            http.setRequestHeader("Access-Control-Allow-Origin", "Content-Type");
            http.onload = function () {
            };
            //http.send("time=5");
            http.send("time=" + time + "&lesson=" + document.getElementById("lesson").value + "&duration=" + player.getDuration());
            }

            }
            }
            });
            }


//            function playerSeekTo(player, seconds) {
//            player.seekTo(seconds);
//            }

            // 4. The API will call this function when the video player is ready.







        </script>

        <script type = "text/javascript">

            var hook = true;
            window.onbeforeunload = function () {
            document.getElementById("frm").submit();
            if (hook) {

            return "Did you save your stuff?";
            }
            }
            function unhook() {
            hook = false;
            }
        </script>
    </body>
</html>
