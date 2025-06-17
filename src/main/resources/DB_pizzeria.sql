CREATE DATABASE pizzeria;
USE pizzeria;

CREATE TABLE pizzas(
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    photo VARCHAR(512) NOT NULL,
    price FLOAT NOT NULL CHECK(price>0)
);

INSERT INTO pizzas (name, description, photo, price) VALUES
('Margherita', 'Pomodoro, mozzarella fior di latte, basilico fresco', 'https://images.openai.com/thumbnails/d94c0736f26f2055278a6c3508d353a5.jpeg', 6.50),
('Marinara', 'Pomodoro, aglio, origano, olio extravergine d’oliva', 'https://ooni.com/cdn/shop/articles/marinara-for-web.jpg?crop=center&height=800&v=1660909857&width=800', 5.50),
('Diavola', 'Pomodoro, mozzarella, salame piccante', 'https://www.pizzarecipe.org/wp-content/uploads/2019/01/Pizza-Diavola.jpg', 7.50),
('Quattro Stagioni', 'Pomodoro, mozzarella, prosciutto cotto, carciofi, funghi, olive nere', 'https://assets.wsimgs.com/wsimgs/ab/images/dp/recipe/202512/0003/img493l.jpg', 8.00),
('Capricciosa', 'Pomodoro, mozzarella, prosciutto, funghi, carciofi, olive', 'https://www.giallozafferano.com/images/293-29343/pizza-capricciosa_1200x800.jpg', 8.00),
('Quattro Formaggi', 'Mozzarella, gorgonzola, fontina, parmigiano', 'https://images.openai.com/thumbnails/70686fc274872ad67a2661296e6fca8e.jpeg', 7.80),
('Bufalina', 'Pomodoro, mozzarella di bufala, basilico fresco', 'https://images.openai.com/thumbnails/98996289438d60ff8d2119084a2567b7.jpeg', 8.50),
('Vegetariana', 'Pomodoro, mozzarella, verdure grigliate', 'https://cdn.shopify.com/s/files/1/0191/9978/files/Pizza-Veggie-Supreme-blog.jpg?v=1652775259', 7.00),
('Tonno e Cipolla', 'Pomodoro, mozzarella, tonno, cipolla rossa', 'https://www.tasteatlas.com/images/dishes/06fb0374204f4cf1bc636a4deb517976.jpg', 7.20),
('Boscaiola', 'Mozzarella, salsiccia, funghi porcini', 'https://blog.giallozafferano.it/cucinaconmiasorella/wp-content/uploads/2019/05/Pizza-alla-boscaiola-bianca-or.jpg', 8.30);

SELECT * FROM pizzas;