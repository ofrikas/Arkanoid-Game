# Arkanoid Game Project

## Overview

This project extends the classic Arkanoid game by adding several new features and improvements. The main objectives are to enhance the game's functionality, support multiple levels, and provide a more engaging user experience through various animations and screens.

## Project Tasks

### Part 1: Reorganization and Pause Feature

- **Code Reorganization**: Refactored the game loop to separate the animation logic from the game-specific logic using the `Animation` interface and `AnimationRunner` class.
- **Pause Feature**: Implemented a pause screen that halts the game when the "p" key is pressed and resumes when the space key is pressed.

### Part 2: Multiple Levels

- **GameLevel Class**: Renamed and refactored the `Game` class to `GameLevel`, accommodating multiple levels in the game.
- **LevelInformation Interface**: Created an interface to define the properties and behaviors of different levels.
- **Levels Implementation**: Developed four classes implementing `LevelInformation` to represent different levels with varying challenges and layouts.
- **GameFlow Class**: Added a `GameFlow` class to manage level transitions and maintain the score across levels.

### Part 3: End Screen

- **End Screen**: Implemented an end screen that displays a message depending on whether the game ended by player failure ("Game Over") or by winning all levels ("You Win"). This screen remains until the space key is pressed.

### Part 4: Further Reorganization

- **KeyPressStoppableAnimation**: Developed a decorator class to handle key-press events more efficiently across different animations (PauseScreen, YouWinAnimation, GameOverAnimation).
- **Bug Fix**: Resolved a bug related to key-press handling between multiple animations to ensure smooth transitions.

## What I Learned

- **Design Patterns**: Gained experience with the template method pattern, the decorator pattern, and how they apply to game development.
- **Code Organization**: Improved skills in refactoring and organizing code to separate concerns and enhance maintainability.
- **Animation and Event Handling**: Learned to implement and manage various animations and user interactions in a game setting.
- **Level Management**: Acquired knowledge on managing multiple game levels, including transitioning between levels and preserving game state.

## Links to Game Assignments

Explore the different assignments that are part of the Arkanoid game development:

- **[Assignment 2: Objects, Geometry, Abstract Art, and Bouncing Balls](https://github.com/ariecattan/biuoop2022/wiki/Assignment-2#objects-geometry-abstract-art-and-bouncing-balls)**
  - Covers the foundational aspects of game objects, geometric shapes, abstract art integration, and basic bouncing ball mechanics.

- **[Assignment 3: Collision Detection, Paddle, and Blocks](https://github.com/ariecattan/biuoop2022/wiki/Assignment-3)**
  - Focuses on implementing collision detection, paddle functionality, and block interactions.

- **[Assignment 5: Block Removal, Lives, and Scores](https://github.com/ariecattan/biuoop2022/wiki/Assignment-5)**
  - Enhances the game with block removal mechanics, player lives management, and scoring systems.

- **[Assignment 6: New Screens and Multiple Levels](https://github.com/ariecattan/biuoop2022/wiki/Assignment-6)**
  - Introduces new game screens and supports multiple levels to increase gameplay depth and complexity.


## Running the Project

To compile and run the project, use the following commands:

```bash
ant compile
ant run
ant -Dargs="1 3 2 1 9 1 bla 3 4 3" run
