create table orders(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
user_id INTEGER NOT NULL,
tour_id INTEGER NOT NULL,
agent_id INTEGER NOT NULL,
cost INTEGER NOT NULL,
FOREIGN KEY (user_id) references users(id),
FOREIGN KEY (tour_id) references tours(id),
FOREIGN KEY (agent_id) references agents(id)
)