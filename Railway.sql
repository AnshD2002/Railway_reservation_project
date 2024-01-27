-- create railway database
create database Railway;

-- Use the railway database
use Railway;

-- create table Users and insert values
create table Users (
	Id int not null auto_increment primary key,
    UserID int(3) not null,
    User_FirstName char(20),
    User_LastName char(20),
    Email_id varchar(30),
    Mobile_no char(11),
    Gender char(6),
    UserPassword varchar(15)
);

insert into Users(UserID ,User_FirstName ,User_LastName ,Email_id ,Mobile_no ,Gender,UserPassword) values
(101, 'Aarav', 'Kumar', 'aarav.kumar@example.com', '9876543210', 'male', 'Aarav9876'),
(102, 'Rakesh', 'Dabral', 'rakesh@example.com', '9432109876', 'male', 'Rakesh9432'),
(103, 'Aanya', 'Verma', 'aanya.verma@example.com', '8765432109', 'female', 'Aanya8765'),
(104, 'Aadi', 'Singh', 'aadi.singh@example.com', '7654321098', 'male', 'Aadi7654'),
(105, 'Advika', 'Sharma', 'advika.sharma@example.com', '6543210987', 'female', 'Advika6543'),
(106, 'Aaryan', 'Patel', 'aaryan.patel@example.com', '5432109876', 'male', 'Aaryan5432'),
(107, 'Anvi', 'Gupta', 'anvi.gupta@example.com', '4321098765', 'female', 'Anvi4321'),
(108, 'Aarush', 'Malik', 'aarush.malik@example.com', '3210987654', 'male', 'Aarush3210');


-- Create railwayplan table
create table railwayplan (
	Id int not null auto_increment primary key,
    train_no int(5) not null ,
    train_name varchar(35),
    start_city char(15),
    stop_city char(15),
    arrival_time varchar(8),
    departure_time varchar(8)
);

insert into railwayplan (train_no, train_name, start_city, stop_city, arrival_time, departure_time) values
    (14152, 'Rajdhani Express', 'Delhi', 'Mumbai', '08:00 AM', '08:15 AM'),
    (14458, 'Shatabdi Express', 'Kolkata', 'Chennai', '10:30 AM', '10:45 AM'),
    (10125, 'Duronto Express', 'Mumbai', 'Delhi', '02:00 PM', '02:15 PM'),
    (44105, 'Gatimaan Express', 'Agra', 'Jaipur', '04:45 PM', '05:00 PM'),
    (52142, 'Howrah Mail', 'Chennai', 'Kolkata', '07:30 PM', '07:45 PM'),
    (75421, 'Deccan Queen', 'Mumbai', 'Pune', '11:00 PM', '11:15 PM'),
    (65214, 'Himalayan Queen', 'Delhi', 'Shimla', '01:30 AM', '01:45 AM');

-- Create booked_seats table
create table booked_seats (Id int not null auto_increment primary key,
		Seat_no varchar(8) not null,
		train_no int(5),
		UserID int(3),
		User_name char(50),
		Status char(15));

-- create trainnum_entry for easily fetch last booked train
create table trainnum_entry (Id int not null auto_increment primary key,trainno int(5));

-- create Userentry for easily fetch last logined user
create table Userentry(Id int not null auto_increment primary key,UserID int(3) not null,User_Name char(50));

select * from Railway.Users;
select * from Railway.railwayplan;
select * from Railway.booked_seats;
select * from Railway.trainnum_entry;
select * from Railway.Userentry;
