--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.22
-- Dumped by pg_dump version 9.5.22

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: assessment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.assessment (
    issuing_student bigint NOT NULL,
    receiver_student bigint NOT NULL,
    workgroup bigint NOT NULL,
    grade double precision NOT NULL
);


ALTER TABLE public.assessment OWNER TO postgres;

--
-- Name: class; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.class (
    course bigint NOT NULL,
    name character varying(255) NOT NULL,
    year character varying(255) NOT NULL,
    professor bigint
);


ALTER TABLE public.class OWNER TO postgres;

--
-- Name: configuration; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.configuration (
    id bigint NOT NULL,
    property character varying(255) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.configuration OWNER TO postgres;

--
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.country (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE public.country OWNER TO postgres;

--
-- Name: country_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.country_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.country_id_seq OWNER TO postgres;

--
-- Name: country_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;


--
-- Name: course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.course (
    id bigint NOT NULL,
    name character varying(255),
    instituition bigint
);


ALTER TABLE public.course OWNER TO postgres;

--
-- Name: doubt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.doubt (
    id bigint NOT NULL,
    answer text,
    answer_date timestamp without time zone,
    creation_date timestamp without time zone NOT NULL,
    question text NOT NULL,
    student bigint NOT NULL,
    workgroup bigint NOT NULL
);


ALTER TABLE public.doubt OWNER TO postgres;

--
-- Name: feedback; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.feedback (
    id bigint NOT NULL,
    content text NOT NULL,
    grade double precision,
    type character varying(255),
    course bigint,
    professor bigint,
    project bigint NOT NULL,
    step_order bigint NOT NULL,
    workgroup bigint
);


ALTER TABLE public.feedback OWNER TO postgres;

--
-- Name: file; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.file (
    id bigint NOT NULL,
    data oid,
    url character varying(255),
    student bigint,
    workgroup bigint
);


ALTER TABLE public.file OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: instituition; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.instituition (
    id bigint NOT NULL,
    address character varying(255),
    name character varying(255),
    phone character varying(255),
    country bigint NOT NULL
);


ALTER TABLE public.instituition OWNER TO postgres;

--
-- Name: instituition_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.instituition_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.instituition_id_seq OWNER TO postgres;

--
-- Name: instituition_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.instituition_id_seq OWNED BY public.instituition.id;


--
-- Name: invite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.invite (
    id bigint NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    issuing_student bigint NOT NULL,
    receiver_student bigint NOT NULL,
    workgroup bigint NOT NULL
);


ALTER TABLE public.invite OWNER TO postgres;

--
-- Name: lective_course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lective_course (
    course bigint NOT NULL,
    professor bigint NOT NULL,
    year character varying(255) NOT NULL
);


ALTER TABLE public.lective_course OWNER TO postgres;

--
-- Name: professor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.professor (
    id bigint NOT NULL,
    email character varying(255),
    encrypted_nic character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    number character varying(255),
    password character varying(255),
    photo_path character varying(255),
    user_type character varying(255),
    country bigint NOT NULL,
    instituition bigint NOT NULL
);


ALTER TABLE public.professor OWNER TO postgres;

--
-- Name: staff; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.staff (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    password character varying(255),
    user_type character varying(255),
    instituition bigint NOT NULL
);


ALTER TABLE public.staff OWNER TO postgres;

--
-- Name: student; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student (
    id bigint NOT NULL,
    degree character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    encrypted_nic character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    number character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    photo_path character varying(255) NOT NULL,
    user_type character varying(255),
    country bigint,
    instituition bigint
);


ALTER TABLE public.student OWNER TO postgres;

--
-- Name: login; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.login AS
 SELECT student.email,
    student.first_name,
    student.last_name,
    student.password,
    student.user_type,
    student.id
   FROM public.student
UNION
 SELECT professor.email,
    professor.first_name,
    professor.last_name,
    professor.password,
    professor.user_type,
    professor.id
   FROM public.professor
UNION
 SELECT staff.email,
    staff.first_name,
    staff.last_name,
    staff.password,
    staff.user_type,
    staff.id
   FROM public.staff;


ALTER TABLE public.login OWNER TO postgres;

--
-- Name: meeting; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meeting (
    id bigint NOT NULL,
    date_time timestamp without time zone NOT NULL,
    duration integer NOT NULL,
    workgroup bigint NOT NULL
);


ALTER TABLE public.meeting OWNER TO postgres;

--
-- Name: message; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.message (
    id bigint NOT NULL,
    content text NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    answer_to bigint,
    student bigint,
    workgroup bigint
);


ALTER TABLE public.message OWNER TO postgres;

--
-- Name: message_answers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.message_answers (
    message bigint NOT NULL,
    answer bigint NOT NULL
);


ALTER TABLE public.message_answers OWNER TO postgres;

--
-- Name: project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.project (
    id bigint NOT NULL,
    class_restriction boolean NOT NULL,
    description character varying(255),
    end_date timestamp without time zone NOT NULL,
    max_elems integer NOT NULL,
    min_elems integer NOT NULL,
    name character varying(255) NOT NULL,
    start_date timestamp without time zone NOT NULL,
    status boolean NOT NULL,
    course bigint NOT NULL,
    professor bigint NOT NULL,
    year character varying(255) NOT NULL
);


ALTER TABLE public.project OWNER TO postgres;

--
-- Name: project_workgroup; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.project_workgroup (
    project bigint NOT NULL,
    workgroup bigint NOT NULL
);


ALTER TABLE public.project_workgroup OWNER TO postgres;

--
-- Name: step; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.step (
    project bigint NOT NULL,
    step_order bigint NOT NULL,
    end_date timestamp without time zone NOT NULL,
    name character varying(255) NOT NULL,
    objetives text NOT NULL,
    star_date timestamp without time zone NOT NULL
);


ALTER TABLE public.step OWNER TO postgres;

--
-- Name: step_task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.step_task (
    id bigint NOT NULL,
    content text NOT NULL,
    project bigint,
    step_order bigint
);


ALTER TABLE public.step_task OWNER TO postgres;

--
-- Name: student_class; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student_class (
    student bigint NOT NULL,
    course bigint NOT NULL,
    name character varying(255) NOT NULL,
    year character varying(255) NOT NULL
);


ALTER TABLE public.student_class OWNER TO postgres;

--
-- Name: student_workgroup; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.student_workgroup (
    student bigint NOT NULL,
    workgroup bigint NOT NULL
);


ALTER TABLE public.student_workgroup OWNER TO postgres;

--
-- Name: task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.task (
    id bigint NOT NULL,
    content text NOT NULL,
    state boolean NOT NULL,
    workgroup bigint,
    project bigint NOT NULL,
    step_order bigint NOT NULL
);


ALTER TABLE public.task OWNER TO postgres;

--
-- Name: workgroup; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workgroup (
    id bigint NOT NULL,
    project bigint,
    course bigint,
    name character varying(255),
    year character varying(255)
);


ALTER TABLE public.workgroup OWNER TO postgres;

--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.instituition ALTER COLUMN id SET DEFAULT nextval('public.instituition_id_seq'::regclass);


--
-- Data for Name: assessment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.assessment (issuing_student, receiver_student, workgroup, grade) FROM stdin;
\.


--
-- Data for Name: class; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.class (course, name, year, professor) FROM stdin;
1	TP1	2019/2020	1
1	TP2	2019/2020	1
\.


--
-- Data for Name: configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.configuration (id, property, value) FROM stdin;
\.


--
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.country (id, name) FROM stdin;
1	Portugal
\.


--
-- Name: country_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.country_id_seq', 1, false);


--
-- Data for Name: course; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.course (id, name, instituition) FROM stdin;
1	Disciplina 1	1
2	Disciplina 2	1
\.


--
-- Data for Name: doubt; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.doubt (id, answer, answer_date, creation_date, question, student, workgroup) FROM stdin;
\.


--
-- Data for Name: feedback; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.feedback (id, content, grade, type, course, professor, project, step_order, workgroup) FROM stdin;
\.


--
-- Data for Name: file; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.file (id, data, url, student, workgroup) FROM stdin;
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- Data for Name: instituition; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.instituition (id, address, name, phone, country) FROM stdin;
1	address	Faculdade de Ciências	+351 …	1
\.


--
-- Name: instituition_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.instituition_id_seq', 1, false);


--
-- Data for Name: invite; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.invite (id, creation_date, issuing_student, receiver_student, workgroup) FROM stdin;
\.


--
-- Data for Name: lective_course; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lective_course (course, professor, year) FROM stdin;
1	1	2019/2020
2	1	2018/2019
\.


--
-- Data for Name: meeting; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.meeting (id, date_time, duration, workgroup) FROM stdin;
\.


--
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.message (id, content, creation_date, answer_to, student, workgroup) FROM stdin;
\.


--
-- Data for Name: message_answers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.message_answers (message, answer) FROM stdin;
\.


--
-- Data for Name: professor; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.professor (id, email, encrypted_nic, first_name, last_name, number, password, photo_path, user_type, country, instituition) FROM stdin;
1	professor@email.com	encrypted nic	Professor 1	Professor 1	FC11111	$2a$10$wv2Kr4d9lL9cndKzmswK3e3M1RC1ldRSx.ZwxWCaE/DofGu0pdazK	photo-path	PROFESSOR	1	1
\.


--
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.project (id, class_restriction, description, end_date, max_elems, min_elems, name, start_date, status, course, professor, year) FROM stdin;
1	f	description	2020-05-31 19:05:46.480321	2	3	Projeto1	2020-05-31 19:05:46.480321	t	1	1	2019/2020
\.


--
-- Data for Name: project_workgroup; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.project_workgroup (project, workgroup) FROM stdin;
\.


--
-- Data for Name: staff; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.staff (id, email, first_name, last_name, password, user_type, instituition) FROM stdin;
1	staff@email.com	Staff1	Staff1	$2a$10$wv2Kr4d9lL9cndKzmswK3e3M1RC1ldRSx.ZwxWCaE/DofGu0pdazK	STAFF	1
\.


--
-- Data for Name: step; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.step (project, step_order, end_date, name, objetives, star_date) FROM stdin;
\.


--
-- Data for Name: step_task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.step_task (id, content, project, step_order) FROM stdin;
\.


--
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student (id, degree, email, encrypted_nic, first_name, last_name, number, password, photo_path, user_type, country, instituition) FROM stdin;
1	degree	email@aluno.com	enrypted nic	Aluno1	Aluno1	FC51111	$2a$10$wv2Kr4d9lL9cndKzmswK3e3M1RC1ldRSx.ZwxWCaE/DofGu0pdazK	photo-path	STUDENT	1	1
2	degree	email2@aluno.com	enrypted nic	Aluno2	Aluno2	FC5122	$2a$10$wv2Kr4d9lL9cndKzmswK3e3M1RC1ldRSx.ZwxWCaE/DofGu0pdazK	photo-path	STUDENT	1	1
\.


--
-- Data for Name: student_class; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student_class (student, course, name, year) FROM stdin;
1	1	TP1	2019/2020
2	1	TP1	2019/2020
\.


--
-- Data for Name: student_workgroup; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.student_workgroup (student, workgroup) FROM stdin;
\.


--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.task (id, content, state, workgroup, project, step_order) FROM stdin;
\.


--
-- Data for Name: workgroup; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.workgroup (id, project, course, name, year) FROM stdin;
\.


--
-- Name: assessment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assessment
    ADD CONSTRAINT assessment_pkey PRIMARY KEY (issuing_student, receiver_student, workgroup);


--
-- Name: class_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.class
    ADD CONSTRAINT class_pkey PRIMARY KEY (course, name, year);


--
-- Name: configuration_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.configuration
    ADD CONSTRAINT configuration_pkey PRIMARY KEY (id);


--
-- Name: country_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);


--
-- Name: course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT course_pkey PRIMARY KEY (id);


--
-- Name: doubt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doubt
    ADD CONSTRAINT doubt_pkey PRIMARY KEY (id);


--
-- Name: feedback_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_pkey PRIMARY KEY (id);


--
-- Name: file_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.file
    ADD CONSTRAINT file_pkey PRIMARY KEY (id);


--
-- Name: instituition_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.instituition
    ADD CONSTRAINT instituition_pkey PRIMARY KEY (id);


--
-- Name: invite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invite
    ADD CONSTRAINT invite_pkey PRIMARY KEY (id);


--
-- Name: lective_course_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lective_course
    ADD CONSTRAINT lective_course_pkey PRIMARY KEY (course, professor, year);


--
-- Name: meeting_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting
    ADD CONSTRAINT meeting_pkey PRIMARY KEY (id);


--
-- Name: message_answers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message_answers
    ADD CONSTRAINT message_answers_pkey PRIMARY KEY (message, answer);


--
-- Name: message_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- Name: professor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT professor_pkey PRIMARY KEY (id);


--
-- Name: project_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id);


--
-- Name: project_workgroup_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_workgroup
    ADD CONSTRAINT project_workgroup_pkey PRIMARY KEY (project, workgroup);


--
-- Name: staff_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff
    ADD CONSTRAINT staff_pkey PRIMARY KEY (id);


--
-- Name: step_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.step
    ADD CONSTRAINT step_pkey PRIMARY KEY (project, step_order);


--
-- Name: step_task_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.step_task
    ADD CONSTRAINT step_task_pkey PRIMARY KEY (id);


--
-- Name: student_class_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_class
    ADD CONSTRAINT student_class_pkey PRIMARY KEY (student, course, name, year);


--
-- Name: student_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- Name: student_workgroup_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_workgroup
    ADD CONSTRAINT student_workgroup_pkey PRIMARY KEY (student, workgroup);


--
-- Name: task_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);


--
-- Name: uk_1hr47j09ffdlpgspj8pdoo955; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message_answers
    ADD CONSTRAINT uk_1hr47j09ffdlpgspj8pdoo955 UNIQUE (answer);


--
-- Name: uk_fe0i52si7ybu0wjedj6motiim; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT uk_fe0i52si7ybu0wjedj6motiim UNIQUE (email);


--
-- Name: uk_il569eb44f0nnmldqcd3drcfc; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT uk_il569eb44f0nnmldqcd3drcfc UNIQUE (number);


--
-- Name: uk_llidyp77h6xkeokpbmoy710d4; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT uk_llidyp77h6xkeokpbmoy710d4 UNIQUE (name);


--
-- Name: uk_p4524c4gbrt76rgbiaf1uuorl; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT uk_p4524c4gbrt76rgbiaf1uuorl UNIQUE (number);


--
-- Name: uk_pvctx4dbua9qh4p4s3gm3scrh; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff
    ADD CONSTRAINT uk_pvctx4dbua9qh4p4s3gm3scrh UNIQUE (email);


--
-- Name: uk_qctgv0g5ymcjgxel9u0h3wijs; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_workgroup
    ADD CONSTRAINT uk_qctgv0g5ymcjgxel9u0h3wijs UNIQUE (workgroup);


--
-- Name: uk_qjm28ojevoom770jyieljec3e; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT uk_qjm28ojevoom770jyieljec3e UNIQUE (email);


--
-- Name: workgroup_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workgroup
    ADD CONSTRAINT workgroup_pkey PRIMARY KEY (id);


--
-- Name: fk232122f8sqomfh4c708y223nh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_workgroup
    ADD CONSTRAINT fk232122f8sqomfh4c708y223nh FOREIGN KEY (workgroup) REFERENCES public.workgroup(id);


--
-- Name: fk2k7kdvgq2oumvkuy5miy8l084; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assessment
    ADD CONSTRAINT fk2k7kdvgq2oumvkuy5miy8l084 FOREIGN KEY (workgroup) REFERENCES public.workgroup(id);


--
-- Name: fk3ofsqyxlgakfgyivcqhjjhja0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_class
    ADD CONSTRAINT fk3ofsqyxlgakfgyivcqhjjhja0 FOREIGN KEY (student) REFERENCES public.student(id);


--
-- Name: fk469rpk9iu2imetvf7yyrx3hog; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT fk469rpk9iu2imetvf7yyrx3hog FOREIGN KEY (course, professor, year) REFERENCES public.lective_course(course, professor, year);


--
-- Name: fk5jtxes25lbc9bkvbj4etr9af3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.class
    ADD CONSTRAINT fk5jtxes25lbc9bkvbj4etr9af3 FOREIGN KEY (course, professor, year) REFERENCES public.lective_course(course, professor, year);


--
-- Name: fk5r2i1ah2ys4itdtl9hl4yjud0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT fk5r2i1ah2ys4itdtl9hl4yjud0 FOREIGN KEY (country) REFERENCES public.country(id);


--
-- Name: fk60sgs7jyf9xvbrp1ctt93a0i1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workgroup
    ADD CONSTRAINT fk60sgs7jyf9xvbrp1ctt93a0i1 FOREIGN KEY (course, name, year) REFERENCES public.class(course, name, year);


--
-- Name: fk757rpr90glodwe6lqljcky0r2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting
    ADD CONSTRAINT fk757rpr90glodwe6lqljcky0r2 FOREIGN KEY (workgroup) REFERENCES public.workgroup(id);


--
-- Name: fk7mkwndy3vqpi69luog8xr4auo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message_answers
    ADD CONSTRAINT fk7mkwndy3vqpi69luog8xr4auo FOREIGN KEY (answer) REFERENCES public.message(id);


--
-- Name: fk819u5g24bkahd9vb62u7vm4p1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT fk819u5g24bkahd9vb62u7vm4p1 FOREIGN KEY (workgroup) REFERENCES public.workgroup(id);


--
-- Name: fk8oi02n9o5o71mwhxecier0jao; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.instituition
    ADD CONSTRAINT fk8oi02n9o5o71mwhxecier0jao FOREIGN KEY (country) REFERENCES public.country(id);


--
-- Name: fk95jkpjnj6iw2sreapa99s7060; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.step_task
    ADD CONSTRAINT fk95jkpjnj6iw2sreapa99s7060 FOREIGN KEY (project, step_order) REFERENCES public.step(project, step_order);


--
-- Name: fkar4j2byie82qnu6ehhu0qgr7j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fkar4j2byie82qnu6ehhu0qgr7j FOREIGN KEY (student) REFERENCES public.student(id);


--
-- Name: fkcb74xp5l0pf6u5ip4rycb2abc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT fkcb74xp5l0pf6u5ip4rycb2abc FOREIGN KEY (instituition) REFERENCES public.instituition(id);


--
-- Name: fkcpym3rpbk0pys8rmf3kc9hi3v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_class
    ADD CONSTRAINT fkcpym3rpbk0pys8rmf3kc9hi3v FOREIGN KEY (course, name, year) REFERENCES public.class(course, name, year);


--
-- Name: fke0cu1i9ncnap74ddwxt8u6s2a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doubt
    ADD CONSTRAINT fke0cu1i9ncnap74ddwxt8u6s2a FOREIGN KEY (workgroup) REFERENCES public.workgroup(id);


--
-- Name: fke9g9j8kh5evr5ulc0tpmt8tdi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fke9g9j8kh5evr5ulc0tpmt8tdi FOREIGN KEY (workgroup) REFERENCES public.workgroup(id);


--
-- Name: fkf107aaifnhqhncr8sq21phgky; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT fkf107aaifnhqhncr8sq21phgky FOREIGN KEY (project, step_order) REFERENCES public.step(project, step_order);


--
-- Name: fkfpgjynbkeb7k5kh7b5dny45s5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workgroup
    ADD CONSTRAINT fkfpgjynbkeb7k5kh7b5dny45s5 FOREIGN KEY (project) REFERENCES public.project(id);


--
-- Name: fkgw1sva4485609ocfkdk5qvk98; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lective_course
    ADD CONSTRAINT fkgw1sva4485609ocfkdk5qvk98 FOREIGN KEY (course) REFERENCES public.course(id);


--
-- Name: fki6uj1xhxxo4vrdcet6005wqvh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff
    ADD CONSTRAINT fki6uj1xhxxo4vrdcet6005wqvh FOREIGN KEY (instituition) REFERENCES public.instituition(id);


--
-- Name: fki9i4378c6fnikeocxm5mtv8gb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_workgroup
    ADD CONSTRAINT fki9i4378c6fnikeocxm5mtv8gb FOREIGN KEY (workgroup) REFERENCES public.workgroup(id);


--
-- Name: fkiforx0xybicjrrt25dfjsko6d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT fkiforx0xybicjrrt25dfjsko6d FOREIGN KEY (course) REFERENCES public.course(id);


--
-- Name: fkijo4yb2n6kfua6wlgnfbgiu2d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invite
    ADD CONSTRAINT fkijo4yb2n6kfua6wlgnfbgiu2d FOREIGN KEY (issuing_student) REFERENCES public.student(id);


--
-- Name: fkist7jaj4sisuw18s4wal5nny2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT fkist7jaj4sisuw18s4wal5nny2 FOREIGN KEY (project, step_order) REFERENCES public.step(project, step_order);


--
-- Name: fkj2hnp1jk6bjyvhx4niq4941tm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fkj2hnp1jk6bjyvhx4niq4941tm FOREIGN KEY (country) REFERENCES public.country(id);


--
-- Name: fkjmtiv04s874x7r73w6bx5xjvr; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.step
    ADD CONSTRAINT fkjmtiv04s874x7r73w6bx5xjvr FOREIGN KEY (project) REFERENCES public.project(id);


--
-- Name: fkjuok9f05p1sdk957w7r5975of; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT fkjuok9f05p1sdk957w7r5975of FOREIGN KEY (workgroup) REFERENCES public.workgroup(id);


--
-- Name: fkk4stkccgfs9fes14xiw7cirh2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message_answers
    ADD CONSTRAINT fkk4stkccgfs9fes14xiw7cirh2 FOREIGN KEY (message) REFERENCES public.message(id);


--
-- Name: fkkh7y5gkfp0l79wln59pji94r6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student_workgroup
    ADD CONSTRAINT fkkh7y5gkfp0l79wln59pji94r6 FOREIGN KEY (student) REFERENCES public.student(id);


--
-- Name: fkklyirq6aru20gtxiub4ifdn11; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.message
    ADD CONSTRAINT fkklyirq6aru20gtxiub4ifdn11 FOREIGN KEY (answer_to) REFERENCES public.message(id);


--
-- Name: fkls4j0xhi7gp49rnfdysos5qan; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.file
    ADD CONSTRAINT fkls4j0xhi7gp49rnfdysos5qan FOREIGN KEY (workgroup) REFERENCES public.workgroup(id);


--
-- Name: fklweqtcoobr4jhcw47hklo2231; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project_workgroup
    ADD CONSTRAINT fklweqtcoobr4jhcw47hklo2231 FOREIGN KEY (project) REFERENCES public.project(id);


--
-- Name: fkm6aumpsjlvvl13rjy6ma0lw03; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assessment
    ADD CONSTRAINT fkm6aumpsjlvvl13rjy6ma0lw03 FOREIGN KEY (issuing_student) REFERENCES public.student(id);


--
-- Name: fkmhhwm65mk9ua75un8t623mtf8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lective_course
    ADD CONSTRAINT fkmhhwm65mk9ua75un8t623mtf8 FOREIGN KEY (professor) REFERENCES public.professor(id);


--
-- Name: fknffaq8bhqi35tiarsp6iqt9ev; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.file
    ADD CONSTRAINT fknffaq8bhqi35tiarsp6iqt9ev FOREIGN KEY (student) REFERENCES public.student(id);


--
-- Name: fkos7a2le9n2sh3lyxa0vi76pgq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fkos7a2le9n2sh3lyxa0vi76pgq FOREIGN KEY (instituition) REFERENCES public.instituition(id);


--
-- Name: fkox6485gxm8veg0nyf1qqo0n52; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT fkox6485gxm8veg0nyf1qqo0n52 FOREIGN KEY (professor) REFERENCES public.professor(id);


--
-- Name: fkqclqsr8gxs6l6pswihp1gimwc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professor
    ADD CONSTRAINT fkqclqsr8gxs6l6pswihp1gimwc FOREIGN KEY (instituition) REFERENCES public.instituition(id);


--
-- Name: fkro8ys4992v8kelix6rphrkny3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.doubt
    ADD CONSTRAINT fkro8ys4992v8kelix6rphrkny3 FOREIGN KEY (student) REFERENCES public.student(id);


--
-- Name: fkt7trdq72f329jpa9lksk280ob; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invite
    ADD CONSTRAINT fkt7trdq72f329jpa9lksk280ob FOREIGN KEY (receiver_student) REFERENCES public.student(id);


--
-- Name: fkt8wse0sxlgl3369l7a7xpt8er; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.invite
    ADD CONSTRAINT fkt8wse0sxlgl3369l7a7xpt8er FOREIGN KEY (workgroup) REFERENCES public.workgroup(id);


--
-- Name: fkte91f3su687ieuh61iov38snh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.assessment
    ADD CONSTRAINT fkte91f3su687ieuh61iov38snh FOREIGN KEY (receiver_student) REFERENCES public.student(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

