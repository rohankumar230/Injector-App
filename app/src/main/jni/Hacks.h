#ifndef HACKS_H
#define HACKS_H

#include "socket.h"
#include "login.h"
Request request;
Response response;
float x,y;
char extra[30];
int botCount,playerCount;
Color clr,clrHealth;
Options options{1, -1, 3, false, 1, false, 401};

Color colorByDistance(int distance, float alpha){
    Color _colorByDistance;
    if (distance < 600)
        _colorByDistance = Color(255,0,150, alpha);
    if (distance < 300)
        _colorByDistance = Color(255,255,0, alpha);
    if (distance < 150)
        _colorByDistance = Color(255,133,51, alpha);
    if (distance < 50)
        _colorByDistance = Color(255,0,0, alpha);
    return _colorByDistance;
}

void DrawESP(ESP esp, int screenWidth, int screenHeight) {


      botCount=0;playerCount=0;
esp.DrawText(Color(255, 0, 0, 255),  "JOKER CHEAT", Vec2( screenWidth / 2, screenHeight / 6.16), 50);

   request.ScreenHeight=screenHeight;
   request.ScreenWidth=screenWidth;
   request.Mode=InitMode;
   request.options=options;
   send((void*)&request,sizeof(request));
   receive((void*)&response);

    esp.DrawTextName(Color::White(), "",
                     Vec2(screenWidth / 2 + screenHeight / 40 + 50, screenHeight / 10.8 - 5),
                     screenHeight / 35);

        if(response.Success) {
        float textsize=screenHeight/50;
        for (int i = 0; i < response.PlayerCount; i++) {
               x = response.Players[i].HeadLocation.x;
               y = response.Players[i].HeadLocation.y;
                if (response.Players[i].isBot) {
                    botCount++;
                    clr.r = 255;
                    clr.g = 255;
                    clr.b = 255;
                    clr.a = 255;
                } else {
                    playerCount++;
                    clr.r = 255;
                    clr.g = 0;
                    clr.b = 0;
                    clr.a = 255;
                }
                float magic_number = (response.Players[i].Distance * response.fov);
                float mx = (screenWidth / 4) / magic_number;
                float my = (screenWidth / 1.38) / magic_number;
                float top = y - my + (screenWidth / 1.7) / magic_number;
                float bottom = y + my + screenHeight / 4 / magic_number;
                Color _colorByDistance = colorByDistance((int)response.Players[i].Distance, 128);
			if(options.openState==0)
				esp.DrawCircle(Color::Green(),
                         Vec2(screenWidth/2,screenHeight/2),request.options.aimingRange,screenHeight/500);
               if (response.Players[i].HeadLocation.z != 1) {
                if (x > -50 && x < screenWidth + 50) {//onScreen
                    if (isSkelton && response.Players[i].Bone.isBone &&
                        !response.Players[i].isBot) {  //Skelton
						    if (response.Players[i].Health < 25)
                                clrHealth = Color(255, 0, 0);
                            else if (response.Players[i].Health < 50)
                                clrHealth = Color(255, 205, 0);
                            else if (response.Players[i].Health < 75)
                                clrHealth = Color(255, 255, 0);
                            else
                                clrHealth = Color(0, 255, 0);
                        esp.DrawLine(clrHealth, 2.1, Vec2(x, y),
                                     Vec2(response.Players[i].Bone.neck.x,
                                          response.Players[i].Bone.neck.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.neck.x,
                                                              response.Players[i].Bone.neck.y),
                                     Vec2(response.Players[i].Bone.cheast.x,
                                          response.Players[i].Bone.cheast.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.cheast.x,
                                                              response.Players[i].Bone.cheast.y),
                                     Vec2(response.Players[i].Bone.pelvis.x,
                                          response.Players[i].Bone.pelvis.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.neck.x,
                                                              response.Players[i].Bone.neck.y),
                                     Vec2(response.Players[i].Bone.lSh.x,
                                          response.Players[i].Bone.lSh.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.neck.x,
                                                              response.Players[i].Bone.neck.y),
                                     Vec2(response.Players[i].Bone.rSh.x,
                                          response.Players[i].Bone.rSh.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.lSh.x,
                                                              response.Players[i].Bone.lSh.y),
                                     Vec2(response.Players[i].Bone.lElb.x,
                                          response.Players[i].Bone.lElb.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.rSh.x,
                                                              response.Players[i].Bone.rSh.y),
                                     Vec2(response.Players[i].Bone.rElb.x,
                                          response.Players[i].Bone.rElb.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.lElb.x,
                                                              response.Players[i].Bone.lElb.y),
                                     Vec2(response.Players[i].Bone.lWr.x,
                                          response.Players[i].Bone.lWr.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.rElb.x,
                                                              response.Players[i].Bone.rElb.y),
                                     Vec2(response.Players[i].Bone.rWr.x,
                                          response.Players[i].Bone.rWr.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.pelvis.x,
                                                              response.Players[i].Bone.pelvis.y),
                                     Vec2(response.Players[i].Bone.lTh.x,
                                          response.Players[i].Bone.lTh.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.pelvis.x,
                                                              response.Players[i].Bone.pelvis.y),
                                     Vec2(response.Players[i].Bone.rTh.x,
                                          response.Players[i].Bone.rTh.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.lTh.x,
                                                              response.Players[i].Bone.lTh.y),
                                     Vec2(response.Players[i].Bone.lKn.x,
                                          response.Players[i].Bone.lKn.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.rTh.x,
                                                              response.Players[i].Bone.rTh.y),
                                     Vec2(response.Players[i].Bone.rKn.x,
                                          response.Players[i].Bone.rKn.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.lKn.x,
                                                              response.Players[i].Bone.lKn.y),
                                     Vec2(response.Players[i].Bone.lAn.x,
                                          response.Players[i].Bone.lAn.y));
                        esp.DrawLine(clrHealth, 2.1, Vec2(response.Players[i].Bone.rKn.x,
                                                              response.Players[i].Bone.rKn.y),
                                     Vec2(response.Players[i].Bone.rAn.x,
                                          response.Players[i].Bone.rAn.y));
                    }


                        //Box
                   if (isPlayerBox) {
                        if (response.Players[i].isBot) {
 	                            esp.DrawRect(Color::White(),
                                        screenHeight / 500, Vec2(x - mx, top),
                                        Vec2(x + mx, bottom));
                        } else {
                            esp.DrawRect(Color::Red(),
                                        screenHeight / 500, Vec2(x - mx, top),
                                        Vec2(x + mx, bottom));
                        }
                    }

					if (isPlayerBoxFill) {
                        if (response.Players[i].isBot) {
							    esp.DrawFilledRect(Color(0, 255, 0,50),
                                        Vec2(x - mx, top),
                                        Vec2(x + mx, bottom));
 	                            esp.DrawRect(Color::White(),
                                        screenHeight / 500, Vec2(x - mx, top),
                                        Vec2(x + mx, bottom));
                        } else {
							esp.DrawFilledRect(Color(255, 0, 0,50),
                                        Vec2(x - mx, top),
                                        Vec2(x + mx, bottom));
                            esp.DrawRect(Color::Red(),
                                        screenHeight / 500, Vec2(x - mx, top),
                                        Vec2(x + mx, bottom));
                        }
                    } 
						
                        //Health
                        if (isPlayerHealth) {
						
                         float healthLength = screenWidth / 25;
                        if (healthLength < mx)
                            healthLength = mx;

                        if (response.Players[i].Health < 25)
                            clrHealth = Color(222, 42, 45, 110);
                        else if (response.Players[i].Health < 50)
                            clrHealth = Color(222, 42, 45, 110);
                        else if (response.Players[i].Health < 75)
                            clrHealth = Color(222, 42, 45, 110);
                        else
                            clrHealth = Color(222, 42, 45, 110);
                        if (response.Players[i].Health == 0)
                            esp.DrawText(Color(255, 0, 0), "Knocked",
                                             Vec2(x, top - screenHeight / 999), textsize),
                                       screenHeight / 27;
                        else {
                            esp.DrawFilledRect(clrHealth,
                                                   Vec2(x - healthLength, top - screenHeight / 30),
                                                   Vec2(x - healthLength + (2 * healthLength) * response.Players[i].Health /100, top - screenHeight / 110));
                                esp.DrawRect(Color(255, 255, 255, 150), screenHeight / 660,
                                             Vec2(x - healthLength, top - screenHeight / 30),
                                             Vec2(x + healthLength, top - screenHeight / 110));
                              }
                           }
                        //Head
                        if (isPlayerHead) {                            
                            esp.DrawCircle(clr,
                                     Vec2(response.Players[i].HeadLocation.x,
                                          response.Players[i].HeadLocation.y -3),
                                     screenHeight / 8 / magic_number, 2);
                        }
                        //Name and distance
                           if (isPlayerName && response.Players[i].isBot) {
                               esp.DrawText(Color(255,255,255),"[AI]",
                                    Vec2(x, top - screenHeight / 65),
                                         textsize-3);                       
                                
                            } else if (isPlayerName) {
                                esp.DrawName(Color().White(), response.Players[i].PlayerNameByte,
                                            response.Players[i].TeamID,
                                             Vec2(x, top - screenHeight / 65), textsize-3); 
							} if (isPlayerDist) {
                            sprintf(extra, "%0.0f m", response.Players[i].Distance);
                            esp.DrawText(Color(255, 165, 0), extra,
                                         Vec2(x, bottom + screenHeight / 50),
                                         textsize);
										 
                        }
                        //weapon
                        if (isEnemyWeapon && response.Players[i].Weapon.isWeapon)
                            esp.DrawWeapon(Color(255, 222, 128), response.Players[i].Weapon.id,
                                           response.Players[i].Weapon.ammo,
                                           Vec2(x, bottom + screenHeight / 27), textsize);
                    }
                }

                //360 Alert
                 if (response.Players[i].HeadLocation.z == 1.0f) {
                if (!isr360Alert)
                    continue;
                if (y > screenHeight - screenHeight / 12)
                    y = screenHeight - screenHeight / 12;
                else if (y < screenHeight / 12)
                    y = screenHeight / 12;
                if (x < screenWidth / 2) {
                    esp.DrawFilledCircle(Color(255, 0, 0, 80), Vec2(screenWidth, y),
                                         screenHeight / 18);
                    sprintf(extra, "%0.0f m", response.Players[i].Distance);
                    esp.DrawText(Color(180, 250, 181, 200), extra,
                                 Vec2(screenWidth - screenWidth / 80, y), textsize);
                } else {
                    esp.DrawFilledCircle(Color(255, 0, 0, 80), Vec2(0, y),
                                         screenHeight / 18);
                    sprintf(extra, "%0.0f m", response.Players[i].Distance);
                    esp.DrawText(Color(180, 250, 181, 200), extra,
                                 Vec2(screenWidth / 80, y), textsize);
                }

                if (x > screenWidth - screenWidth / 12)
                    x = screenWidth - screenWidth / 12;
                else if (x < screenWidth / 12)
                    x = screenWidth / 12;
                if (y < screenHeight / 2) {
                    esp.DrawFilledCircle(Color(255, 133, 51), Vec2(x, screenHeight),
                                         screenHeight / 18);
                    sprintf(extra, "%0.0f m", response.Players[i].Distance);
                    esp.DrawText(Color(180, 250, 181, 200), extra,
                                 Vec2(x, screenHeight - screenHeight / 80), textsize);
                } else {
                    esp.DrawFilledCircle(Color(255, 133, 51), Vec2(x, 0),
                                         screenHeight / 18);
                    sprintf(extra, "%0.0f m", response.Players[i].Distance);
                    esp.DrawText(Color(180, 250, 181, 200), extra,
                                 Vec2(x, screenHeight / 80 + 10), textsize);
                }

            } 
            else if (x < -screenWidth / 10 || x > screenWidth + screenWidth / 10) {
                if (!isr360Alert)
                    continue;
                if (y > screenHeight - screenHeight / 12)
                    y = screenHeight - screenHeight / 12;
                else if (y < screenHeight / 12)
                    y = screenHeight / 12;
                if (x > screenWidth / 2) {
                    esp.DrawFilledCircle(Color(255, 0, 0, 80), Vec2(screenWidth, y),
                                         screenHeight / 18);
                    sprintf(extra, "%0.0f m", response.Players[i].Distance);
                    esp.DrawText(Color(180, 250, 181, 200), extra,
                                 Vec2(screenWidth - screenWidth / 80, y), textsize);
                } else {
                    esp.DrawFilledCircle(Color(255, 0, 0, 80), Vec2(0, y),
                                         screenHeight / 18);
                    sprintf(extra, "%0.0f m", response.Players[i].Distance);
                    esp.DrawText(Color(180, 250, 181, 200), extra,
                                 Vec2(screenWidth / 80, y), textsize);
                }

            } 
            else if (y < -screenHeight / 10 ||  y > screenHeight + screenHeight / 10) {
                if (!isr360Alert)
                    continue;
                if (x > screenWidth - screenWidth / 12)
                    x = screenWidth - screenWidth / 12;
                else if (x < screenWidth / 12)
                    x = screenWidth / 12;
                if (y > screenHeight / 2) {
                    esp.DrawFilledCircle(Color(255, 133, 51), Vec2(x, screenHeight),screenHeight / 18);
                    sprintf(extra, "%0.0f m", response.Players[i].Distance);
                    esp.DrawText(Color(180, 250, 181, 200), extra,
                                 Vec2(x, screenHeight - screenHeight / 80), textsize);
                } else {
                    esp.DrawFilledCircle(Color(255, 133, 51), Vec2(x, 0),
                                         screenHeight / 18);
                    sprintf(extra, "%0.0f m", response.Players[i].Distance);
                    esp.DrawText(Color(180, 250, 181, 200), extra,
                                 Vec2(x, screenHeight / 80 + 10), textsize);
                }
                    }
              else if(isPlayerLineTop)
                 esp.DrawLine(clr, screenHeight / 500,
                           Vec2(screenWidth / 2, screenHeight / 10.5 + 8), Vec2(x, top));
			  else if (isPlayerLineMiddle)
                      esp.DrawLine(clr, screenHeight / 500,
                                 Vec2(screenWidth / 2, screenHeight / 2), Vec2(x, top));
			   else if (isPlayerLineBottom)
                      esp.DrawLine(clr, screenHeight / 500,
                                 Vec2(screenWidth / 2, screenHeight), Vec2(x, top));
            }
 if (botCount + playerCount > 0) {
            esp.DrawFilledRect(Color(181, 2, 2,80),
                                    Vec2(screenWidth / 2 - screenHeight / 15, 
                                    screenHeight / 20),
                                    Vec2(screenWidth / 2 + screenHeight / 15, 
                                    screenHeight / 10));
        esp.DrawRect(Color(181, 2, 2),2,
                                    Vec2(screenWidth / 2 - screenHeight / 15, 
                                    screenHeight / 20),
                                    Vec2(screenWidth / 2 + screenHeight / 15, 
                                    screenHeight / 10));
          esp.DrawRect(Color(181, 2, 2),2,
                                    Vec2(screenWidth / 2 - screenHeight / 15, 
                                    screenHeight / 20),
                                    Vec2(screenWidth / 2 + screenHeight / 15, 
                                    screenHeight / 10));                          
            sprintf(extra, "%d", playerCount + botCount);
              esp.DrawText(Color(255,255,255), extra, Vec2(screenWidth / 2,
              screenHeight / 11.5),
                        screenHeight / 30);               
                     } 
   else if (botCount + playerCount == 0){           
              esp.DrawFilledRect(Color(2, 181, 62,80),
                                    Vec2(screenWidth / 2 - screenHeight / 15, 
                                    screenHeight / 20),
                                    Vec2(screenWidth / 2 + screenHeight / 15, 
                                    screenHeight / 10));
            esp.DrawRect(Color(2, 181, 62),2,
                                    Vec2(screenWidth / 2 - screenHeight / 15, 
                                    screenHeight / 20),
                                    Vec2(screenWidth / 2 + screenHeight / 15, 
                                    screenHeight / 10));
             esp.DrawRect(Color(2, 181, 62),2,
                                    Vec2(screenWidth / 2 - screenHeight / 15, 
                                    screenHeight / 20),
                                    Vec2(screenWidth / 2 + screenHeight / 15, 
                                    screenHeight / 10));                       
              esp.DrawText(Color(255, 255, 255), "CLEAR", Vec2(screenWidth / 2,
              screenHeight / 11.5),
                     screenHeight / 30);  
    
	
        }
     }

            for (int i = 0; i < response.GrenadeCount; i++){
                if(!isGrenadeWarning)
                    continue;
                esp.DrawText(Color(255, 0, 0),"HAT JA BC NADE HAI ",Vec2(screenWidth/2,screenHeight/8),15);
                if(response.Grenade[i].Location.z!=1.0f){
                         if(response.Grenade[i].type==1 )
                        esp.DrawText(Color(255, 0, 0),"Grenade",Vec2(response.Grenade[i].Location.x,response.Grenade[i].Location.y),15);
                    else
                        esp.DrawText(Color(255, 158, 89),"Molotov",Vec2(response.Grenade[i].Location.x,response.Grenade[i].Location.y),15);
                }
            }
            for(int i = 0; i < response.VehicleCount; i++){
                if(response.Vehicles[i].Location.z!=1.0f) {
                    esp.DrawVehicles(response.Vehicles[i].VehicleName,response.Vehicles[i].Distance,Vec2(response.Vehicles[i].Location.x,response.Vehicles[i].Location.y),15);

                }
            }
           for(int i = 0; i < response.ItemsCount; i++){
               if(response.Items[i].Location.z!=1.0f) {
                   esp.DrawItems(response.Items[i].ItemName,response.Items[i].Distance,Vec2(response.Items[i].Location.x,response.Items[i].Location.y),15);
             }
         }
     }


#endif //DESI_ESP_IMPORTANT_HACKS_H
