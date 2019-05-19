create table tours(
id INTEGER primary key AUTO_INCREMENT,
tour_agency INTEGER NOT NULL,
tour_name VARCHAR(255),
tour_description VARCHAR(255),
FOREIGN KEY (tour_agency) references agencies(id)
)