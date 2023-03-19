1. // Create the trajectory generator
TrajectoryGenerator generator = new TrajectoryGenerator();

// Create the backward trajectory
TrajectoryConfig backwardConfig = new TrajectoryConfig(2.0, 2.0);
backwardConfig.setReversed(true);
Trajectory backwardTrajectory = generator.generateTrajectory(
   new Pose2d(0, 0, new Rotation2d(0)),
   List.of(),
   new Pose2d(0, -1, new Rotation2d(0)),
   backwardConfig
);

// Create the forward trajectory
TrajectoryConfig forwardConfig = new TrajectoryConfig(2.0, 2.0);
Trajectory forwardTrajectory = generator.generateTrajectory(
   new Pose2d(0, -1, new Rotation2d(0)),
   List.of(),
   new Pose2d(0, 1, new Rotation2d(0)),
   forwardConfig
);


2. // Create the Ramsete command for the backward trajectory
RamseteCommand backwardCommand = new RamseteCommand(
   backwardTrajectory,
   robotDrive::getPose,
   new RamseteController(2.0, 0.7),
   new SimpleMotorFeedforward(1, 3),
   robotDrive.getSwerveDriveKinematics(),
   robotDrive::getWheelSpeeds,
   new PIDController(1, 0, 0),
   new PIDController(1, 0, 0),
   robotDrive::setModuleStates,
   robotDrive
);
backwardCommand.schedule();
Timer.delay(0.5);

// Create the Ramsete command for the forward trajectory
RamseteCommand forwardCommand = new RamseteCommand(
   forwardTrajectory,
   robotDrive::getPose,
   new RamseteController(2.0, 0.7),
   new SimpleMotorFeedforward(1, 3),
   robotDrive.getSwerveDriveKinematics(),
   robotDrive::getWheelSpeeds,
   new PIDController(1, 0, 0),
   new PIDController(1, 0, 0),
   robotDrive::setModuleStates,
   robotDrive
);
forwardCommand.schedule();
Timer.delay(2.0);


3. robotDrive.stop();


4. public void autonomousInit() {
   // Create the autonomous command group
   CommandGroupBase autonomousCommand = new SequentialCommandGroup(
      new SwerveTrajectoriesAutoCommand()
   );

   // Schedule the autonomous command group
   autonomousCommand.schedule();
}