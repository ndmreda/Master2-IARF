clear all; % clear all variables
close all; % close all figures
clc;       % clear command window

%1)
img = imread("lenna.tif");
img=double(img)./255; 
SNR = 20;
[ib,bruit,sigma] = genererimage(img,SNR);

figure(1);
title("image bruité")
imagesc(ib);
colormap(gray);
colorbar; 

%2)
    %a)
J = wiener2(ib,[5,5]);
figure(2);
title("Filtre wiener2")
imagesc(J);
colormap(gray);
colorbar; 
%b)
img2 = wavelet(ib,2);
%seillage 
%sigma_img = sum((img - mean(img)).^2)/(length(img) - 1);
%Varier T de 0.1 a 0.5
%T = sigma*sqrt(2)*log(length(img));
siz = size(img2);
T=0.4;
%dure
doux=img2;
dure=img2;

sz_dr = size(dure);
for i = 1:sz_dr(1)
    for j = 1:sz_dr(2)
        if doux(i,j) > T
           doux(i,j)= 0;
        end
    end
end

seill_doux = invwavelet(doux,2);

figure(3);
title("seuillage dure manuelle")
imagesc(seill_doux);
colormap(gray);
colorbar; 

%doux
 sz_dx = size(doux)
for i = 1: sz_dx(1)
    for j = 1: sz_dx(2)
        if abs(dure(i,j)) <= T
           dure(i,j)= 0;
        end
        if dure(i,j) > T
           dure(i,j)= dure(i,j)- T;
        end
        if dure(i,j) < -T
           dure(i,j)= dure(i,j)+ T;
        end
    end
end


seuill_dure = invwavelet(dure,2);
figure(4);
title("seuillage doux manuelle")
imagesc(seuill_dure);
colormap(gray);
colorbar; 


%seillage image avec fct matlab 
img_hard = wthresh(ib,'h',0.4);
img_soft = wthresh(ib,'s',0.4);
%img_hard = invwavelet(img_hard,2);
%img_soft = invwavelet(img_hard,2);


figure(5);
title("seuillage dure")
imagesc(img_hard);
colormap(gray);
colorbar; 

figure(6);
title("seuillage doux")
imagesc(img_soft);
colormap(gray);
colorbar; 
%c)
img_nn_local = imnlmfilt(img)
figure(7);
title("moyen non local")
imagesc(img_nn_local);
colormap(gray);
colorbar; 


%Déconvolution 


