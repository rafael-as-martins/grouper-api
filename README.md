# Grouper REST API

# REST PATTERN
 This API was made based on HATEOAS REST Pattern.

# Deploy
 Execute build.sh script on the grouper-api directory, after the execution you should have an up and running REST API available on port 8080

# Docker

Some docker commands thats can be use externaly to the build.sh script

 docker-compose up — It will look into docker-compose.yml file and start up each specified service, the service setup commands and on their correspondent DockerFile files.\
 docker-compose down — It will stop and remove all containers (Including volumes)\
 docker-compose up --build — Any update done on the application or configuration level, this command should be executed in order to apply them.

Note: After startup, you should the following containers up and running:

  - grouper-db  
  - grouper-api-app
  
  In order to get into the container execute the follwoing command:
    
     - docker exec -ti <container-name> bash
  

The database is in postgres, here you will find the commands to get into the database:

 psql grouper postgres -> Get Into de DB
 \dt -> Show schema tables

 
# API Endpoints (72)

## SignIn

GET: http://{dominio}/api/auth

JSON Request

* email: String
* password: String

JSON Response

* jwtAuthenticationResponse: JwtAuthenticationResponse
* roles: Collection
* name: String 
* id: Integer

## Assessment

GET: http://{dominio}/api/assessment

JSON Request 

* issuerId: Long
* receiverId: Long
* workgroupId: Long

JSON Response

* issuerId: Long
* receiverId: Long
* workgroupId: Long
* grade: Double

POST: http://{dominio}/api/assessment

JSON Request 

* issuerId: Long
* receiverId: Long
* workgroupId: Long

## Class

GET: http://{dominio}/api/class/{year}/course/{courseId}/{name}

Params:

* year: String
* courseId: Long
* name: String

## Configuration

GET: http://{dominio}/api/configuration/{property}

Params:

* property: String

## Country

GET: http://{dominio}/api/country/{id}

Params:

* id: Long

## Course

GET: http://{domain}/api/courses

JSON Request

* name: String
* total: Long
* page: Integer
* pagesize Integer

JSON Response

* name: String
* page: Integer
* pagesize Integer
* Links to Courses: link-rel

GET: http://{domain}/api/course/projects

JSON Request:

* courseId: Long
* year: String

JSON Response

* courseId: Long
* year: String
* Links to projects: link-rel

GET: http://{domain}/api/course/{id}

Params:

* id: Long

GET: http://{domain}/api/course/students

JSON Request

* year: String
* courseId: Long
* search: String
* orderField: String
* page: Integer
* pageSize: Integer
* withGroups: Boolean

JSON BODY

* year: String
* courseId: Long
* search: String
* orderField: String
* page: Integer
* pageSize: Integer
* withGroups: Boolean
* Links for courses: link-rel

## Doubt

GET: http://{dominio}/api/doubt/{id}

Params: 

* id: Long

POST: http://{dominio}/api/doubt

JSON Request

* question: String
* studentId: Long
* workgroupId: Long

JSON Reposnse

* id: Long
* question: String
* creationDate: Date
* answer: String
* answerDate: Date
* studentId: Long
* workgroupId: Long

PATCH: http://{dominio}/api/doubt

JSON Request

* id: Long
* answer: String

## Feedback


GET: http://{dominio}/api/feedback/{id}

Params:

* id: Long

PUT: http://{dominio}/api/feedback/{id}

JSON Request

* workgroupId: Long
* professorId: Long
* courseId: Long
* stepOrder: Long
* type: FeedbackType (FORMAL, SUMMATIVE)
* content: String
* grade: String

PUT: http://{dominio}/api/feedback

JSON Request

* workgroupId: Long
* professorId: Long
* courseId: Long
* stepOrder: Long
* type: FeedbackType (FORMAL, SUMMATIVE)
* content: String
* grade: String
 
JSON Response

* workgroupId: Long
* professorId: Long
* courseId: Long
* stepOrder: Long
* type: FeedbackType (FORMAL, SUMMATIVE)
* content: String
* grade: String
* id: Long

## File

POST: http://localhost:8080/api/upload/csv

Params:

* fileType: String (STUDENT, PROFESSOR, COURSE, LECTIVE_COURSE, CLASS, STUDENT_CLASS)

GET: http://{dominio}/api/file/{fileId}

Params:

* fileId: Long

POST: http://{dominio}/api/upload/{workgroupId}/{studentId}

Params: 

* workgroupId: Long
* studentId: Long

JSON Response:

* studentId: Long
* workgroupId: Long
* name: String
* type: String
* size: Long

DELETE: http://{dominio}/api/file/{fileId}

Params: 

* fileId: Long


## Instituition

GET: http://{dominio}/api/instituition/{id}

Params: 

* id: Long

## Invite

GET: http://{dominio}/api/invite/{id}

Params:

* id: Long

JSON Response

* id: Long 
* creationDate: Date
* issuingStudent: Long
* receiverStudent: Long
* workgroupId: Long

POST: http://{dominio}/api/invite

JSON Request

* issuingStudent: Long
* receiverStudent: Long
* workgroupId: Long


## Lective Course

GET: http://{dominio}/api/lectiveCourse/{year}/course/{courseId}/professor/{professorId}

Params:

* year: String
* courseId: Long
* professorId: Long

JSON Response

* year:
* Links to professor: link-rel
* Links to course: link-rel


## Meeting


GET: http://{dominio}/api/meeting/{id}

Params:

* id: Long

JSON Response

* id: Long
* dateTime: Date
* duration: Integer
* workgroupId: Long
* Link to workgroup: link-rel

POST: http://{dominio}/api/meeting

JSON Request

* dateTime: String
* duration: Integer
* workgroupId: Long

GET: http://{dominio}/api/meetings

JSON Request

* workgroupId: Long

JSON Response

* workgroupId: Long
* Links to meetings: link-rel

PATCH: http://{dominio}/api/meeting

JSON Request

* dateTime: String
* duration: Integer

## Message

GET: http://{dominio}/api/message/{id}

Params: 

* id: Long

JSON Response

* id: Long
* content: String
* studentId: Long
* workgroupId: Long
* creationDate: Date
* answerTo: Long
* Link to Student: link-rel
* Link to Workgroup: link-rel
* Link to answers: link-rel

POST: http://{dominio}/api/message

JSON Request

* content: String
* answerTo: Long (Optional)
* studentId: Long
* workgroupId: Long

## Professor

GET: http://{domain}/api/professors

JSON Request

* search: String (rated to fields number, firstName, lastName, email)
* page: Integer
* total: Long
* pagesize Integer

JSON Response

* search: String (rated to fields number, firstName, lastName, email)
* page: Integer
* pagesize Integer
* Links to Professors: link-rel

GET: http://{dominio}/api/professor/courses

JSON Request

* year: String
* professorId: Long

JSON Request

* year: String
* professorId: Long
* Link to courses: link-rel


GET: http://{dominio}/api/professor/{id}

Params: 

* id: Long 

JSON Response

* id: Long
* firstName: String
* lastName: String
* number: String
* email: String
* encryptedNic: String
* photoPath: String
* Link to Institution: link-rel
* Link to Country: link-rel
* Links to lectiveCourses

PUT: http://{dominio}/api/professor/{id}

JSON REQUEST:

* firstName: String
* lastName: String
* number: String
* email: String
* encryptedNic: String
* photoPath: String
* password: String

GET: http://{dominio}/api/professor/courses/projects

JSON Request

* year: String
* professorId: Long
* courseId: Long

JSON Response

* year: String
* professorId: Long
* courseId: Long
* Links to projects: link-rel


## Project 

PUT: http://{domain}/api/project

JSON Request:

* id: Long
* name: String
* startDate: Date
* endDate: Date
* minElems: Integer
* maxElems: Integer
* classRestriction: Boolean
* status: Boolean

GET: http://{domain}/api/project/{id}

Params:

* id: Long

JSON Response:

* id: Long
* name: String
* startDate: Date
* endDate: Date
* minElems: Integer
* maxElems: Integer
* classRestriction: Boolean
* status: Boolean

PATCH: http://{domain}/api/project/endDate

JSON Request

* projectId: Long
* endDate: String


PATCH: http://{domain}/api/project/status

JSON Request

* projectId: Long
* status: String

POST: http://<dominio>/api/project

JSON Request:

* name: String
* startDate: String
* endDate: String
* minElems: Integer
* maxElems: Integer
* classRestriction: Boolean
* professorId: Long
* courseId: Long
* year: String
* status: Boolean


JSON Response

* id: Long


GET: http://{dominio}/api/project/steps

JSON Request

* projectId: Long

JSON Response

* projectId: Long
* Links to Steps: link-rel


GET: http://{dominio}/api/project/doubts


JSON Request

* projectId: Long
 JSON Response

* projectId: Long
* Links to Doubts: link-rel


GET: http://{dominio}/api/project/workgroups

JSON Request

* projectId: Long
* search: String
* orderField: String (FIRSTNAME, LASTNAME, STUDENT_NUMBER, EMAIL, CLASS)
* page: Integer 
* pageSize: Integer
 JSON Response

* projectId: Long
* Links to Doubts: link-rel

Delete: http://{dominio}/api/project/{projectId}

Params:

* projectId: Long

## Staff


GET: http://{dominio}/api/staff/{id}

Params:

* id: Long

JSON Response

* id: Long
* firstName: String
* lastName: String
* fullName: String
* email: String
* Link to Institution: link-rel

## Step 

PUT: http://{domain}/api/step

JSON Request:

* projectId: Long
* stepOrder: Long
* name: String
* objetives: String
* startDate: Date
* endDate: Date


GET: http://{domain}/api/step/step-tasks

JSON Request:

* projectId: Long
* stepOrder: Long

JSON Response:

* projectId: Long
* stepOrder: Long
* Links to StepTasks: link-rel


GET: http://{domain}/api/step/tasks

JSON Request

* projectId: Long
* stepOrder: Long

JSON Response

* projectId: Long
* stepOrder: Long
* Links to Tasks: link-rel


GET: http://{domain}/api/step/{order}/project/{projectId}

Params:

* order: Long
* projectId: Long

JSON Response

* stepOrder: Long
* name: String
* objetives: String
* startDate: Date
* endDate: Date
* projectId: Long
* Link to project: link-rel

POST: http://{dominio}/api/step

JSON Request: 

* projectId: Long
* name: String
* objetives: String
* startDate: String
* endDate: String


PATCH: http://{dominio}/api/step

JSON Request

* projectId: Long
* stepOrder: Long
* endDate: String

DELETE: http://{dominio}/api/step

JSON Request

* projectId: Long
* stepOrder: Long


## Step Task

GET: http://{dominio}/api/stepTask/{id}

Params:

* id: Long

JSON Response

* id: Long
* content: String
* Link to Step: link-rel
* Link to Workgroup: link-rel


POST: http://{dominio}/api/stepTask

JSON Request:

* projectId: Long
* stepOrder: Long
* content: String
* workgroupId: Long


## Student

GET: http://{domain}/api/students

JSON Request

* search: String (rated to fields id, firstName, lastName, email, degree)
* page: Integer
* total: Long
* pagesize Integer

JSON Response

* search: String (rated to fields id, firstName, lastName, email, degree)
* page: Integer
* pagesize Integer
* Links to Students: link-rel

GET: http://{dominio}/api/student/{id}

Params:

* id: Long

JSON Response

* id: Long
* firstName: String
* lastName: String
* degree: String
* number: String
* email: String
* photoPath: String
* countryId: Long
* encryptedNic: String
* Link to Institution: link-rel
* Link to Workgroups: link-rel
* Link to Country: link-rel
* Links to Classes: link-rel


GET: http://{dominio}/api/student/courses

JSON Request

* studentId: Long
* year: String

JSON Response

* studentId: Long
* year: String
* Links to courses: link-rel


PUT: http://{dominio}/api/student/{id}


Params:

* id: Long

JSON Request:

* firstName: String
* lastName: String
* degree: String
* number: String
* email: String
* photoPath: String
* encryptedNic: String
* password: String
* instituitionId: Long
* countryId: Long

POST: http://{dominio}/api/student

JSON Request

* firstName: String
* lastName: String
* degree: String
* number: String
* email: String
* photoPath: String
* countryId: Long
* encryptedNic: String


GET:  http://{dominio}/api/student/courses/projects

JSON Request

* year: String
* studentId: Long
* courseId: Long

JSON Response

* year: String
* studentId: Long
* courseId: Long
* Links to project: link-rel


GET:  http://{dominio}/api/student/class

JSON Request

* courseId: String
* studentId: Long
* className: Long
* year: String

JSON Response

* courseId: String
* studentId: Long
* className: Long
* year: String
* Links to class: link-rel

DELETE:  http://{dominio}/api/student/workgroup

JSON Request

* studentId: Long
* workgroupId: Long


## Task 

GET:  http://{dominio}/api/task/{id}

Params:

* id: Long

JSON Response

* id:Long
* content: String
* state: Boolean
* workgroupId: Long
* stepOrder: Long
* projectId: Long
* Link to Step

PUT:  http://{dominio}/api/task/{id}

Params:

* id: Long

JSON Request

* content: String
* state: Boolean

POST:  http://{dominio}/api/task

JSON Request

* workgroupId
* content: String
* state: Boolean
* stepOrder: Long
* projectId: Long

PATCH:  http://{dominio}/api/task

JSON Request

* content: String
* state: Boolean

## Workgroup

GET:  http://{dominio}/api/student/{studentId}/workgroups

Params:

* studentId: Long

JSON Response (Collection of)

* Links to Workgroups: link-rel

GET:  http://{dominio}/api/project/{projectId}/workgroups

Params:

* projectId: Long

JSON Response (Collection of)

* Links to Workgroups: link-rel

GET:  http://{dominio}/api/workgroup/{id}

Params:

* id: Long

JSON Response

* id: Long
* Link to Project: link-rel
* Link to class: link-rel
* Links to students: link-rel

POST:  http://{dominio}/api/workgroup/associate

JSON Request:

* projectId: Long
* studentId: Long
* workgroupId: Long (Optional)
* className: String (Optional)
* year: String (Optional)

GET:  http://{dominio}/api/workgroup/messages

JSON Request

* workgroupId: Long

JSON Response

* workgroupId: Long
* Links to Message: link-rel

GET:  http://{dominio}/api/workgroup/doubts

JSON Request

* workgroupId

JSON Response

* workgroupId: Long
* Links to Doubts: link-rel

GET:  http://{dominio}/api/workgroup/tasks

JSON Request

* workgroupId

JSON Response

* workgroupId: Long
* Links to Tasks: link-rel


GET:  http://{dominio}/api/workgroup/feedbacks

JSON Request

* workgroupId

JSON Response

* workgroupId: Long
* Links to Tasks: link-rel
