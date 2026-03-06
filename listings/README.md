# Listings API
This API handles everything associated with listings. This means creation of users/items, creation of listings, and searching of listings.

# System Architecture

![System Design.png](images/System%20Design.png)

The external game server will query the api. The API interacts with the database to store/retreive any data which needs to persist.
When a listing is created, it is published to a kafka topic.