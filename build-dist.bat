:: Copies everything into dist & builds zip
@echo off
echo Cleaning dist
if exist dist rd /s /q dist
mkdir dist
mkdir dist\settings
echo Copying jar from out
cp out\artifacts\Garlicium_Mining_GUI_jar\Garlicium-Mining-GUI.jar dist\Garlicium-Mining-GUI.jar
echo Copying clean settings
cp settings\settings.clean.ser dist\settings
cp dist\settings\settings.clean.ser dist\settings\settings.ser
echo Zipping:
cd dist
7z a Garlicium-Mining-GUI Garlicium-Mining-GUI.jar settings\*
echo build-dist Done
cd ..
