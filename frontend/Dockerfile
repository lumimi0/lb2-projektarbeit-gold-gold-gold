# Multi-stage Build
FROM node:18-alpine AS builder
WORKDIR /app

# Package files kopieren
COPY package*.json ./
RUN npm ci

# Source code kopieren
COPY . .

# Angular app bauen
RUN npm run build

# Production stage
FROM nginx:alpine

# Built app kopieren - WICHTIG: Korrekter Pfad für Angular 19
COPY --from=builder /app/dist/gold-gold-gold /usr/share/nginx/html

# Nginx config für Angular SPA erstellen
RUN echo 'server {' > /etc/nginx/conf.d/default.conf && \
    echo '    listen 80;' >> /etc/nginx/conf.d/default.conf && \
    echo '    server_name localhost;' >> /etc/nginx/conf.d/default.conf && \
    echo '    root /usr/share/nginx/html;' >> /etc/nginx/conf.d/default.conf && \
    echo '    index index.html;' >> /etc/nginx/conf.d/default.conf && \
    echo '    location / {' >> /etc/nginx/conf.d/default.conf && \
    echo '        try_files $uri $uri/ /index.html;' >> /etc/nginx/conf.d/default.conf && \
    echo '    }' >> /etc/nginx/conf.d/default.conf && \
    echo '}' >> /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
