--// test
-- Migration SQL that makes the change goes here.

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (3, '��������', 'MODULE', 'BUSINESS', '/caseRecords.htm', NULL, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (4, '���ɷ���', 'MODULE', 'BUSINESS', '/laws.htm', NULL, 4);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (5, '��ʦ����', 'MODULE', 'BUSINESS', '/lawyers.htm', NULL, 5);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (6, 'Ͷ�߹���', 'MODULE', 'BUSINESS', '/complaints.htm', NULL, 6);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (7, 'ͳ�Ʒ���', 'MODULE', 'BUSINESS', '/statics.htm', NULL, 7);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (31, '��������', 'LEAF', 'BUSINESS', '/caseRecords.htm', 3, 1);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (41, '���ɷ���', 'LEAF', 'BUSINESS', '/laws.htm', 4, 1);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (51, '��ʦά��', 'LEAF', 'BUSINESS', '/lawyers.htm', 5, 1);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (61, 'Ͷ�߹���', 'LEAF', 'BUSINESS', '/complaints.htm', 6, 1);
--//@UNDO
-- SQL to undo the change goes here.