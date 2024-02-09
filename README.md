Shared library for setting up jenkins
https://www.cloudbees.com/blog/how-to-install-and-run-jenkins-with-docker-compose

# Prerequisites

- Docker: https://www.docker.com/get-started/
- Docker compose

# Initial setup

- Comment `agent` service in `docker-compose.yaml`

### Config in Advanced:

- Get path of java in the container: `which java`, example result: `/opt/java/openjdk/bin/java`

# Pipeline setup

## Add library

- Go to: http://localhost:8080/manage/configure
- Navigate to: Global Pipeline Libraries
- Add library:
  - Name: shared-library
  - Default version: develop
  - Ensure check only: Include @Library changes in job recent changes
  - Retrieval method: Modern SCM
  - Source Code Management: Git
  - Project Repository: https://github.com/duytung95nb/jenkins-shared-library.git
  - Credentials: None
  - Click 'Save' for saving settings

## Add new pipeline

- Go to dashboard: http://localhost:8080
- Click on 'New item' (on the left)
- Fill item name, for example: 'phone-case-fe'
- Item type: Pipeline
- Click 'Ok'
- Add Description
- Pipeline > Definition > Pipeline script from SCM
- SCM: Git
- Repository URL: https://github.com/rawit-TAP/phone-case-fe.git
- Add Credentials: Jenkins
  - Kind: Ssh Username with Private key
  - Copy github private key (if using in local to authenticate): `pbcopy < ~/.ssh/id_ed25519`
  - Fill in: Username and Corresponding Private Key (enter directly)
- Select 'No verification' (not recommended): Manage Jenkins > Configure Global Security > Git host key verification configuration

# Run

- Run: `docker-compose up`
- Access to: `http://localhost:8080`
- Start setup
- While running job, if see `jenkins exited with code 137` then increase Docker memory
