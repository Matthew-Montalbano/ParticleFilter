# Particle Filter

## Table of Contents
* [Introduction](#introduction)
* [Background](#background)
* [Demo](#demo)
* [Technologies](#technologies)
* [Installation](#installation-and-usage)


## Introduction
I created this project as a way to familiarize myself with the algorithm of particle filtering, as well as to introduce myself to Spring Boot as a backend technology.<br>
This project can be used as a playground for a basic particle filtering algorithm so users can understand how the particle filter method works and observe how different settings can lead to different outcomes.<br>

<img width="720" alt="image" src="https://user-images.githubusercontent.com/18200123/204375537-d1ac5da9-59a2-4deb-b423-91eac15ad560.png">

**NOTE**: This repository is only the backend. The frontend repo can be found [here](https://github.com/Matthew-Montalbano/particle_filter_FE).

## Background
Background on the particle filtering method can be found [here](https://en.wikipedia.org/wiki/Particle_filter).

In the context of this project, particle filtering is used to estimate the location of a moving object in a 2D grid and give a look into the motion history of that moving object over the course of a scenario.
Observations based on the true x and y positions of the moving object are randomly generated and used, with a degree of uncertainty, to resample particles.

## Demo
The video below demonstrates a particle filter being run over the course of a scenario using the settings provided on the left-hand side of the window.<br>
In this demo, the true target state of the moving object (pink), the average particle history (dark green), and individual particle histories (light green) are plotted on the graph.<br><br>
![ezgif com-gif-maker](https://user-images.githubusercontent.com/18200123/204369958-cbd2d3bb-a8c4-4e4a-a357-1cbe2fe5897a.gif)

## Technologies
* Java 19
* Maven 4.0.0
* Spring Boot 2.7.0
* Mockito 4.8.0

## Installation and Usage
Clone this repo to your desktop and open up the project in IntelliJ IDEA. In the Maven console, run `mvn clean install` to install all dependencies and build the project.<br><br>
To run the project, navigate to `src/main/java/org/example/mattmontalbano/particlefilter/api`, right click `ParticleFilterApplication.java` and click run. 
