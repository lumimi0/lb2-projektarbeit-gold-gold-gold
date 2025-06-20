FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
RUN npm install -g @angular/cli
COPY . .
RUN ng build

FROM nginx:alpine
COPY --from=build /app/dist/gold-gold-gold /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
