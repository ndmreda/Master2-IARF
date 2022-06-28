clear all; % clear all variables
close all; % close all figures
clc;       % clear command window

%1)
img = imread("lenna.tif");
img=double(img)./255; 
SNR = 20;
[ib,bruit,sigma] = genererimage(img,SNR);

figure();
subplot(421);
imagesc(ib);
colormap(gray);
title("image bruité");
colorbar; 

%2)
    %a)
J = wiener2(ib,[5,5]);
subplot(422);
imagesc(J);
colormap(gray);
title("Filtre wiener2");
colorbar; 
%b)
img2 = wavelet(ib,2);
%seillage 
%sigma_img = sum((img - mean(img)).^2)/(length(img) - 1);
%Varier T de 0.1 a 0.5
T = sigma*sqrt(2)*log(length(img));
siz = size(img2);
%T=0.4;
%dure
doux=img2;
dure=img2;

sz_dr = size(dure);
for i = 1:sz_dr(2)
    for j = 1:sz_dr(1)
        if dure(i,j) <= T
           dure(i,j)= 0;
        end
    end
end

seill_dure = invwavelet(dure,2);

subplot(423);
imagesc(seill_dure);
colormap(gray);
title("seuillage dure manuelle");
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
subplot(424);
imagesc(seuill_dure);
colormap(gray);
title("seuillage doux manuelle");
colorbar; 


%seillage image avec fct matlab 
img_hard = wthresh(ib,'h',0.4);
img_soft = wthresh(ib,'s',0.4);
%img_hard = invwavelet(img_hard,2);
%img_soft = invwavelet(img_hard,2);


%subplot(144);
imagesc(img_hard);
colormap(gray);
title("seuillage dure");
colorbar; 

%figure(6);
imagesc(img_soft);
colormap(gray);
title("seuillage doux");
colorbar; 
%c)
img_nn_local = imnlmfilt(img)
figure(7);
imagesc(img_nn_local);
colormap(gray);
title("moyen non local");
colorbar; 

 

