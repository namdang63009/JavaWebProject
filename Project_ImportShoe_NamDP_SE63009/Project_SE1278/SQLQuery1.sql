


SELECT id, shoesID,description, sbz.price, sizes, quantityShoeBySize
FROM tbl_shoes
JOIN tbl_shoeBySize as sbz
	ON shoesID = shoeID
JOIN tbl_sizes
	ON id = sizeID
WHERE quantityShoeBySize = 0


Select sizes, id
From tbl_sizes


UPDATE tbl_shoeBySize SET quantityShoeBySize = 0, price = 123 
WHERE shoeID = 's005' AND sizeID='s5'

UPDATE tbl_shoes SET quantityOfShoe = 999 WHERE shoesID = 's005'

UPDATE tbl_sizes SET quantity = 888 WHERE id='s5'

SELECT quantityOfShoe, quantity
FROM tbl_shoes, tbl_sizes
Where shoesID = 's004' and id = 's5'



 select * from tbl_shoes
  select * from tbl_sizes
  select * from tbl_shoeBySize


--search by size
  Select description, quantityOfShoe, sizes
From tbl_shoes, tbl_sizes
where (sizes like '39')
and id in (Select sizeID from tbl_shoeBySize)

--search by descritpion
Select Distinct description, quantityOfShoe, sizes
From tbl_shoes, tbl_sizes
where (description like '%a%')
and id in (Select sizeID from tbl_shoeBySize)