# TODO


## Endpoints

### User

* login
    * JWT provider / checker service?

* view incoming
    * manage incoming
* write outgoing
    * manage outgoing


### Channels

- index
  - human text
  - channel description urls...
    - channel description url
    - human text
    - sinks?
    - sources?
  - other index urls...
    - index url
    - human text

- channel description
  - human text
  - endpoints...
    - url
    - sink or source?
    - authentication
    - identification
    - human text

- source (query params: date range/ordering/limit?)
  - messages...
    - id (globally unique)
      - node_id (ie. server url)
      - msg_id (ie. unique message number for the server)
    - parent ids... (eg. reply to, refers to, continues from, etc)
      - id
    - known child ids...?
      - id
    - created timestamp (ISO 8601 with Timezone)
    - updated timestamp (or maybe unix time?)
    - mime type
    - content
    - dimensions...
      - key
      - numeric(?) value




- hooks?
- ids integral to messages, generated with message, globally unique
- mvp - open public chatroom


TODO:
 - updated timestamps on messages
 - ISO 8601 format for timestamps!!!
 - SPA:
    - index
      - read index
      - render useful info from index
      - select MVC from index (link opens new panel)
    - MVC
      - render scrollable messages view
      - reply to messages directly
      - compose new message
