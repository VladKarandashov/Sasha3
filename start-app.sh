#!/bin/bash
echo "Начинаю выполнение скрипта запуска"

echo "Подтягиваю изменения из Git"
git pull

echo "Собираем сборку"
mvn clean install
sleep 3
echo "Убиваем все приложения"
tmux kill-server
sleep 3
tmux ls
sleep 3

echo "Запускаем приложение"
# Запускаем новую сессию tmux в фоновом режиме
tmux new-session -d -s my-session 'mvn spring-boot:run'
# Отображаем список сессий tmux
sleep 3
tmux list-sessions
sleep 3
tmux ls

echo "Рестартуем nginx"
sleep 3
sudo systemctl restart nginx
sleep 3
sudo systemctl status nginx