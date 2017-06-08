--// csms_init
-- Migration SQL that makes the change goes here.

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (3, '�참���ܿ�', 'MODULE', 'BUSINESS', '/overviews.htm', NULL, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (4, '��������', 'MODULE', 'BUSINESS', '/caseRecords.htm', NULL, 4);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (5, '�����ල', 'MODULE', 'BUSINESS', '/caseWarnings.htm', NULL, 5);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (6, '¼��¼��', 'MODULE', 'BUSINESS', '/caseMedias.htm', NULL, 6);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (7, '��������', 'MODULE', 'BUSINESS', '/bdmPoliceStations.htm', NULL, 7);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (31, '�������', 'LEAF', 'BUSINESS', '/overviews.htm', 3, 1);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (41, '��������', 'GROUP', 'BUSINESS', NULL, 4, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (411, '�ֳ�����', 'LEAF', 'BUSINESS', '/cases/dispute.htm', 41, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (412, '��������', 'LEAF', 'BUSINESS', '/cases/civil.htm', 41, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (413, '���°���', 'LEAF', 'BUSINESS', '/cases/criminal.htm', 41, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (42, '�참��¼', 'LEAF', 'BUSINESS', '/caseRecords.htm', 4, 2);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (51, '���ֹ���', 'GROUP', 'BUSINESS', NULL, 5, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (511, '��������', 'LEAF', 'BUSINESS', '/caseScores/rank.htm', 51, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (512, '�ҵĻ���', 'LEAF', 'BUSINESS', '/caseScores/detail.htm', 51, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (513, '���۱�׼', 'LEAF', 'BUSINESS', '/bdmEvaluations.htm', 51, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (52, '���յȼ�', 'LEAF', 'BUSINESS', '/bdmClassifications.htm', 5, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (53, '�������ල', 'LEAF', 'BUSINESS', '/caseWarnings.htm', 5, 3);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (61, '����Ƶ�б�', 'LEAF', 'BUSINESS', '/caseMedias.htm', 6, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (62, '����Ƶ���', 'LEAF', 'BUSINESS', '/bdmVideoCategories.htm', 6, 2);

INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (71, '��������', 'LEAF', 'BUSINESS', '/bdmPoliceStations.htm', 7, 1);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (72, '�참��ά��', 'LEAF', 'BUSINESS', '/bdmHandlingAreas.htm', 7, 2);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (73, '����ά��', 'LEAF', 'BUSINESS', '/bdmRooms.htm', 7, 3);
INSERT INTO smart_menus (id_, name_, type_, plugin_, url_, parent_id_, display_order_) VALUES (74, '���䲼��', 'LEAF', 'BUSINESS', '/bdmRoomLayout.htm', 7, 4);

--//@UNDO
-- SQL to undo the change goes here.


