# Smart Multi-Storey Car Park

An exercise in object-oriented programming, using a TDD methodology.

## Background

You've been commissioned to build a system for White Lines™ that will be used in their next-generation smart multi-storey car park.

Each parking space in the car park has a particular maximum height and width, and optionally some restrictions on what type of vehicle can park in it.

The car park has cameras and sensors at the entrance and exit. As a vehicle arrives, the cameras detect what type of vehicle it is (car, bike or van), how high and wide it is, and its registration number. Based on this information, the car park will automatically find the nearest parking space that they are allowed to park in, and marks that space as occupied.

When a vehicle leaves, the car park marks the space that it had parked in as empty.

## Part 0 - Setup

Get your own copy of the code by forking and cloning this repository. Once you have the repository locally, run the following command to link it back to this upstream repository.

```
git remote add upstream git@github.com:scl-softwire/multistorey-car-park.git
```

Check that you can run the (almost empty!) `main` method, and you're good to go.

## Part 1 - Vehicles

Firstly, you'll need a way of storing vehicle details in your code. Run the following command to get some failing tests:

```
git cherry-pick red-1
```

Read and run the tests - check that they aren't all passing yet. Understand what's happening in the test, and write some code (in `Vehicle.java`) to make it pass! There should already be a skeleton layout to base your own code on.

## Part 2 - Parking spaces

Secondly, you'll need a way of storing details of particular parking spaces in code. Each parking space has an ID number, a maximum width, a maximum height, and optionally a restriction on what type of vehicle can park in it.

Run the following command:

```
git cherry-pick red-2
```

As before, run the tests, make sure that they fail, and then write the code (this time in `ParkingSpace.java`) to make it pass.

## Part 3 - Parking in a space

It's not much good having a parking space without having any way of actually parking a vehicle in it! Run the following command:

```
git cherry-pick red-3
```

This will add some more tests and skeleton methods to `ParkingSpaceTest.java` and `ParkingSpace.java`. Read and run the tests, check they fail, and write the code to make them pass!

## Part 4 - Floors

Obviously, a multi-storey car park consists of multiple floors. That means it's a good idea to have a class that represents an entire floor of parking spaces.

Each floor has a floor number, and also a collection of parking spaces that belong to that floor.

Get yourself some more tests relating to floors:

```
git cherry-pick red-4
```

As always, run the tests, fail them, and make them pass.

## Part 5 - Finding free spaces

We're getting to the point where we can do something useful with our car park! Each floor is arranged so that the parking spaces are arranged in order of their ID - a smaller ID is closer to the entrance.

Each floor needs to be able to find the nearest unoccupied parking space that is suitable for any given vehicle.

Get some tests for this functionality:

```
git cherry-pick red-5
```

Make sure you understand what each test is checking for, then write the code to make them pass.

## Part 6 - Multi-storey

We can now have a `MultiStorey` class that consists of multiple floors! The multistorey car park needs to be able to, given a particular vehicle, find the closest space that vehicle car park in.

Obviously lower floors are closer to the entrance that higher floors, so you should find a parking space on the lowest possible floor.

Get some tests for this functionality:

```
git cherry-pick red-6
```

Understand the tests, run them, and finally make them pass!

## Part 7 - Finding a vehicle

When a vehicle leaves the car park, we need to mark its old space as empty. That means, given a registration, we need to be able to find the space that the vehicle with that registration is parked in.

There are some tests for that which you can get hold of using:

```
git cherry-pick red-7
```

For the final time, run these tests then make them pass.

## Final Part - All systems go!

The car park is now operational! Yippee!

You want to monitor how it behaves over its first day of operation. The file [spaces.csv](spaces.csv) contains definitions of all the parking spaces that appear in the car park, in CSV format. Make your `main` method read this file, and create a `MultiStorey` that contains the floors and parking spaces that can be found in the file.

The second file [events.txt](events.txt) contains the log of every event that happened over the course of a single day.

Each line in that file is either an "Entry" or an "Exit". Each "Entry" contains the type, registration and dimensions of a vehicle that's entering the car park, and each "Exit" contains the registration of a vehicle that's leaving.

Update your program so that for each event in this file, it finds which parking space that vehicle should park in, parks it in that space, and prints a line containing the vehicle registration and the parking space ID to an output file.

If there are no spaces for the vehicle, then output a line containing the vehicle registration and "no suitable spaces".

Every time a vehicle exits, remove it from its parking space and output a line containing the registration number and "left the car park". Note that there may still be an "Exit" line for vehicles that didn't actually get given a parking space - they still have to leave the car park!

Run your program and get someone to check if your output file is correct!