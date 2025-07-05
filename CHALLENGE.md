**Technical Challenge**

**Design, implement and deploy an email **

**generator service**

**Service Overview**

The Dynamic Email Generator is a highly flexible and secure service that creates customized email addresses by processing user-defined inputs and expressions.

**Core Objectives**

**Email Address Customization**: Enable users to define and generate email addresses using a proprietary expression language that interprets user input through advanced string operations and logic.

**Secure and Scalable API**: Provide a robust API built on modern architectural principles, offering secure, scalable, and efficient interaction points for external systems.

**Implementation Highlights**

**Custom Expression Language**: Develop and integrate a unique expression language that allows for dynamic email generation based on user inputs and predefined rules.

**API Design and Documentation**: Construct a RESTful API that facilitates the interaction with the service, fully documented with Swagger for ease of integration and clarity.

**Security Implementation**: Integrate comprehensive security measures to ensure data integrity and protect against potential threats.

**Deployment Strategy**: Utilize Docker to containerize the service, ensuring scalability and ease of deployment across different environments.

Introduce a comprehensive test suite with unit and integration tests.

Technical Challenge

1

**Documentation and Support**

**Detailed Setup Guide**: Provide a step-by-step guide to setting up and deploying the service, including configuration details and operational instructions.

**User Manual and Examples**: Offer a detailed manual describing how to interact with the service, including examples of using the expression language to generate desired email formats.

**Postman Collection**: Include a Postman collection to facilitate easy testing and demonstration of the service capabilities.

**\[ Mandatory \] Design and Implement Custom **

**Expression Language**

**Input Parameters**

**Dynamic Input Handling:** The service must accept indefinite input parameters via query parameters. These parameters provide the raw data that will be used to construct email addresses according to user-defined rules.

**Example Inputs:**

input1: "Jean"

input2: "Mignard"

input3: "external"

input4: "peoplespheres"

input5: "fr"

input6: "com"

input7: "internal"

input8: "Han"

input9: "Solo"

input10: "Millennium Falcon"

input11: "io"

Parameters are dynamically named as inputN \(the parameter name is just an example\) where N is a sequential number indicating the order of the input.

**Expression Parameter**

Technical Challenge

2

**Customizable Expressions:** Implement functionality that allows users to define how inputs are transformed into an email address via a query parameter named expression .

**Expression Flexibility:** The expression should be capable of incorporating various operations such as character manipulation, conditional logic, and concatenation to format the email address dynamically.

**Guidance for Expression Syntax \(do not implement this syntax, design your own\):** expression="input1.firstChars\(1\) ~ '.' ~ input9.lastChar s\(3\) ~ '@' ~ input7.allChars\(\) ~ '.' ~ input4.allChars\(\)

~ '.' ~ input11.allChars\(\)"

**Expected Output**

**JSON Response Format:** The API should return a JSON object containing the generated email addresses, encapsulating each email in an array to handle multiple possible outputs.

**Example Output:**

\{

"data": \[

\{

"id": "j.olo@internal.peoplespheres.io",

"value": "j.olo@internal.peoplespheres.io"

\}

\]

\}

**Handling Multiple Emails**

**Complex Output Generation:** The expression evaluation must support generating multiple email addresses based on the given inputs and the logic defined in the expression. This feature requires the ability to iterate over inputs or apply expressions that produce several valid outputs.

**Documentation and Examples**

**Detailed Documentation:** Provide thorough documentation of the expression language, including syntax, available functions, and operators. The documentation should guide Technical Challenge

3

users on how to construct their own expressions effectively.

**Practical Examples:** Illustrate various use cases and scenarios where the expression language can be applied. Examples should cover different input configurations, demonstrate conditional logic usage, and show how to handle complex data structures within expressions.

**Operational Guidance:** Include step-by-step examples to demonstrate the expression's application in real-world scenarios, helping users grasp the potential and flexibility of the custom expression language.

**Testing and Validation**

**Purpose and Scope**: Develop a comprehensive suite of unit tests to validate the behavior and functionality of the custom expression language. These tests should ensure that all aspects of string processing and manipulation are working as expected under various scenarios.

**Test Coverage**:

**Functionality Tests**: Each function or operation available in the expression language, such as **firstChars **, **lastChars **, and **allChars **, must be thoroughly tested.

These tests should cover basic functionality, boundary conditions, and error handling cases.

**Expression Evaluation**: Tests should validate the correct parsing and evaluation of complete expressions. This includes checking the integration of different operations within a single expression and ensuring the output is as expected.

**Edge Cases**: Include tests for edge cases such as empty inputs, extremely long strings, and unusual character sets to ensure robustness.

**Performance Tests**: Assess the performance and efficiency of the expression evaluations, particularly focusing on processing speed and memory usage with large and complex inputs.

**\[ Optional \] Implement Persistence**

**Database Selection**

You are free to choose any type of database, whether relational \(e.g., PostgreSQL\) or non-relational \(e.g., MongoDB\). The choice should be justified based on the application's requirements and the specific benefits of the selected database system.

Technical Challenge

4

The decision should consider factors such as the structure of the data, the expected load, scalability needs, and specific features like transactions or JSON support.

**Integration with Spring Application**

Implement the persistence layer using Spring Data. This should include the setup of Spring Data repositories and the configuration of the data source.

Demonstrate the use of transactions where applicable, ensuring data integrity and consistency.

Outline the configuration of the database connection within the Spring application, including details on connection pooling, if applicable, and any optimizations for performance.

**Data Model Design**

Design a data model that supports the operation of the email generator. This might include entities such as:

**Email Template**: Represents the structure of the email expressions.

**Generated Email**: Stores instances of generated emails, potentially linking back to templates or input parameters.

**Functional Implementation**

Implement CRUD \(Create, Read, Update, Delete\) operations for any entities involved in the application.

Demonstrate how the application handles dynamic input parameters and stores results for future retrieval or analytics.

**Testing and Validation**

Include unit and integration tests that cover the persistence layer. Tests should verify the correct persistence of data, the integrity of the data model, and any business logic encapsulated in the database interactions.

Document how to run the tests and any setup required for test environments.

**\[ Optional \] Implement Security**

Technical Challenge

5

**Authentication and Authorization** **Implement Authentication Mechanisms:** Integrate an authentication mechanism suitable for the service. This could include basic authentication, token-based authentication \(JWT\), or OAuth2. Justify the choice based on the application's needs.

**User Authorization:** Implement role-based access control \(RBAC\) to manage what authenticated users can and cannot do within the application. Define at least two roles: Admin \(can access and modify all resources\) and User \(limited to accessing and modifying their own data\).

**Secure Communication**

**HTTPS Configuration:** Ensure all data transmitted between the client and the server is encrypted using HTTPS. Provide configuration details that demonstrate how SSL/TLS is set up in the Spring application.

**API Security:** Secure the API endpoints against common vulnerabilities such as SQL

injection, Cross-Site Scripting \(XSS\), and Cross-Site Request Forgery \(CSRF\). Discuss any libraries or Spring Security configurations used to mitigate these risks.

**Data Security**

**Data Encryption:** Implement encryption for sensitive data both at rest and in transit.

Detail the encryption methods and algorithms used, including any key management practices.

**Secure Data Access:** Ensure that the database queries and updates are performed securely, avoiding injection attacks and ensuring data privacy.

**Testing and Validation**

**Security Testing Strategy:** Develop a comprehensive testing strategy to assess the security of the authentication mechanisms, communication encryption, and data access protocols. This strategy should include:

**Unit Tests:** Write unit tests for custom security functions and utility classes that handle encryption, decryption, and other security-related functions.

**Integration Tests:** Implement integration tests that simulate authenticated sessions and test role-based access control to ensure that security policies are enforced correctly across the application.

Technical Challenge

6

**\[ Mandatory \] Docker Configuration** **Docker Services Setup:**

**Eclipse Temurin Service:**

Utilize the **eclipse-temurin:latest ** public image from Docker Hub to run the Java application.

This service should be configured to run the Spring Boot application, making it accessible to the NGINX server via internal networking.

**NGINX Service:**

Employ the **nginx:latest ** public image as the web server.

**Port Configuration:** Expose NGINX's SSL port 443 on the Docker host at port 9443, allowing secure external access to the web server.

**SSL Configuration:** Set up a self-signed SSL certificate for NGINX to secure communications. Provide instructions or scripts as part of the deployment to generate this certificate.

**Docker Configuration:**

**docker-compose.yaml:**

Define a **docker-compose.yaml ** file that orchestrates the setup of both the Eclipse Temurin and NGINX services.

Ensure that both services are configured to communicate effectively, with NGINX

routing HTTPS requests to the Eclipse Temurin service.

Include volume mappings for NGINX to access configuration files and SSL certificates.

**NGINX Configuration:**

Provide a basic NGINX configuration that routes HTTPS traffic to the Eclipse Temurin service. This configuration should handle SSL termination and proxy requests to the Java application.

Include error handling within the NGINX configuration to manage unavailable service gracefully.

Technical Challenge

7

**\[ Mandatory \] Implement Version Control Best** **Practices**

**Version Control Setup**

**Public Repository Setup:** Init and manage the project within a public repository on a platform such as GitHub or GitLab, including all developmental branches and commits.

**Repository Structure:** Organize the repository in a logical and professional manner **Best Practices in Version Control**

To ensure high standards in code management and team collaboration, adhere to industry-recognized best practices in version control to demonstrate proficiency in managing complex software projects.

**Implementation:** Develop and follow a branching strategy that aligns with the project's complexity and collaboration requirements. You are encouraged to choose a strategy that best fits the project scale and team dynamics, whether it be Git Flow, GitHub Flow, or any other effective workflow.

**Branch Naming, commits, commit messages:** Follow industry’s best practices in branch naming, commits and commit messages.

**Issue Tracking:** Utilize the issue tracking feature of the chosen platform to manage tasks, enhancements, and bugs. Ensure that issues are clearly described Technical Challenge

8



