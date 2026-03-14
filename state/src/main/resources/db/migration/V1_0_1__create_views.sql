CREATE VIEW wallets_view AS
SELECT w.user_id user_id, w.currency_id currency_id, c.name name, w.amount amount
FROM wallets w INNER JOIN currencies c ON w.currency_id = c.id;

CREATE VIEW inventories_view AS
SELECT inv.user_id user_id, inv.item_id item_id, itm.name name, inv.quantity quantity, inv.reserved reserved
FROM inventories inv INNER JOIN items itm ON inv.item_id = itm.id;