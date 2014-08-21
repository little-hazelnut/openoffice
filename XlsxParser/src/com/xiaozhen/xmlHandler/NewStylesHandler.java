package com.xiaozhen.xmlHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.xiaozhen.styles.Alignment;
import com.xiaozhen.styles.Border;
import com.xiaozhen.styles.CellXf;
import com.xiaozhen.styles.ColorStyle;
import com.xiaozhen.styles.Fill;
import com.xiaozhen.styles.Font;
import com.xiaozhen.styles.GradientFillEffects;
import com.xiaozhen.styles.Stop;
import com.xiaozhen.styles.Styles;
import com.xiaozhen.styles.Alignment.Horizontal;
import com.xiaozhen.styles.Alignment.ReadingOrder;
import com.xiaozhen.styles.Alignment.Vertical;
import com.xiaozhen.styles.Border.BorderStyle;
import com.xiaozhen.styles.Fill.PatternType;
import com.xiaozhen.styles.Font.Font_u;
import com.xiaozhen.styles.Font.VertAlign;
import com.xiaozhen.styles.GradientFillEffects.GradientType;
import com.xiaozhen.util.SheetTools;

public class NewStylesHandler extends DefaultHandler {

	private final Map<String, Short> stylesDicMap;
	private String attriValue;
	private Styles styles = Styles.getInstance();
	// numFmts
	private Map<Integer, String> numFmts;
	// fonts
	private List<Font> fonts;
	private Font font;
	// fills
	private List<Fill> fills;
	private Fill fill;
	private ColorStyle colorStyle;
	private GradientFillEffects gFillEffects;
	private Stop stop;
	// borders
	private List<Border> borders;
	private Border border;
	private BorderStyle bstyle;
	// cellxfs
	private List<CellXf> cellXfs;
	private CellXf cellXf;
	private Alignment alignment;

	private boolean startStopTag = false;
	private boolean startFontTag = false;
	private boolean startBordersTag = false;
	private boolean startCellXfsTag = false;
	private boolean applyAlignment = false;

	// numFmts
	private final String TAG_numFmts = "numFmts";
	private final String TAG_numFmt = "numFmt";
	private final String QNAME_formatCode = "formatCode";
	private final String QNAME_numFmtId = "numFmtId";
	// fonts
	private final String TAG_fonts = "fonts";
	private final String TAG_font = "font";
	private final String TAG_sz = "sz";
	private final String TAG_color = "color";
	private final String TAG_name = "name";
	private final String TAG_family = "family";
	private final String TAG_charset = "charset";
	private final String TAG_scheme = "scheme";
	private final String TAG_b = "b";
	private final String TAG_i = "i";
	private final String TAG_strike = "strike";
	private final String TAG_u = "u";
	private final String TAG_vertAlign = "vertAlign";
	private final String QNAME_val = "val";
	private final String QVAL_double = "double";
	private final String QVAL_singleAccounting = "singleAccounting";
	private final String QVAL_doubleAccounting = "doubleAccounting";
	private final String QVAL_superscript = "superscript";
	// fills
	private final String TAG_fills = "fills";
	// fill->patternFill
	private final String TAG_patternFill = "patternFill";
	private final String QNAME_patternType = "patternType";
	// fill->patternFill->fgColor,bgColor
	private final String TAG_fgColor = "fgColor";
	private final String TAG_bgColor = "bgColor";
	// fill->gradientFill
	private final String TAG_gradientFill = "gradientFill";
	private final String QNAME_degree = "degree";
	private final String QNAME_GradientType = "type";
	// fill->gradientFill->stop
	private final String TAG_stop = "stop";
	private final String QNAME_position = "position";
	// fill->patternFill.patternType:
	private final String QVAL_gray125 = "gray125";
	private final String QVAL_darkGray = "darkGray";
	private final String QVAL_mediumGray = "mediumGray";
	private final String QVAL_lightGray = "lightGray";
	private final String QVAL_gray0625 = "gray0625";
	private final String QVAL_darkHorizontal = "darkHorizontal";
	private final String QVAL_darkVertical = "darkVertical";
	private final String QVAL_darkDown = "darkDown";
	private final String QVAL_darkUp = "darkUp";
	private final String QVAL_darkGrid = "darkGrid";
	private final String QVAL_darkTrellis = "darkTrellis";
	private final String QVAL_lightHorizontal = "lightHorizontal";
	private final String QVAL_lightVertical = "lightVertical";
	private final String QVAL_lightDown = "lightDown";
	private final String QVAL_lightUp = "lightUp";
	private final String QVAL_lightGrid = "lightGrid";
	private final String QVAL_lightTrellis = "lightTrellis";
	private final String QVAL_solid = "solid";
	private final String QVAL_none = "none";
	private final String QVAL_path = "path";
	private final String QVAL_linear = "linear";
	// borders
	private final String TAG_borders = "borders";
	private final String TAG_border = "border";
	private final String TAG_diagonal = "diagonal";
	private final String QNAME_diagonalUp = "diagonalUp";
	private final String QNAME_diagonalDown = "diagonalDown";
	private final String QNAME_style = "style";
	private final String QVAL_hair = "hair";
	private final String QVAL_slantDashDot = "slantDashDot";
	private final String QVAL_dotted = "dotted";
	private final String QVAL_mediumDashDot = "mediumDashDot";
	private final String QVAL_dashDotDot = "dashDotDot";
	private final String QVAL_doubleL = "double";
	private final String QVAL_medium = "medium";
	private final String QVAL_mediumDashed = "mediumDashed";
	private final String QVAL_dashDot = "dashDot";
	private final String QVAL_thin = "thin";
	private final String QVAL_dashed = "dashed";
	private final String QVAL_thick = "thick";
	private final String QVAL_mediumDashDotDot = "mediumDashDotDot";
	// cellXfs
	private final String TAG_cellXfs = "cellXfs";
	private final String TAG_xf = "xf";
	private final String TAG_alignment = "alignment";
	private final String QNAME_borderId = "borderId";
	private final String QNAME_fillId = "fillId";
	private final String QNAME_fontId = "fontId";
	private final String QNAME_applyAlignment = "applyAlignment";
	private final String QNAME_applyBorder = "applyBorder";
	private final String QNAME_applyFont = "applyFont";
	private final String QNAME_applyFill = "applyFill";
	private final String QNAME_applyNumberFormat = "applyNumberFormat";
	private final String QNAME_vertical = "vertical";
	private final String QNAME_horizontal = "horizontal";
	private final String QNAME_readingOrder = "readingOrder";
	private final String QNAME_shrinkToFit = "shrinkToFit";
	private final String QNAME_textRotation = "textRotation";
	private final String QNAME_wrapText = "wrapText";
	private final String QNAME_indent = "indent";
	private final String QNAME_justifyLastLine = "justifyLastLine";
	// cellXfs->alignment->vertical、horizontal
	private final String QVAL_center = "center";
	private final String QVAL_justify = "justify";
	private final String QVAL_distributed = "distributed";
	// cellXfs->alignment->horizontal
	private final String QVAL_centerContinuous = "centerContinuous";
	// cellXfs->alignment->readingOrder
	private final String QVAL_roltr = "1"; // left to right
	private final String QVAL_rortl = "2"; // right to left
	// share string -------------------
	// borders,fills,cellXfs->alignment->vertical,
	private final String SSTR_top = "top";
	private final String SSTR_bottom = "bottom";
	// borders,fills,cellXfs->alignment->horizontal
	private final String SSTR_left = "left";
	private final String SSTR_right = "right";
	// fills,cellXfs->alignment->horizontal
	private final String SSTR_fill = "fill";
	// ---------------------------------
	// colorStyle
	private final String QNAME_theme = "theme";
	private final String QNAME_tint = "tint";
	private final String QNAME_rgb = "rgb";
	private final String QNAME_indexed = "indexed";
	private final String QNAME_auto = "auto";

	// number variety
	// numFmts 1-100
	private final short Num_numFmts = 1;
	private final short Num_numFmt = 2;
	private final short Num_formatCode = 3;
	// fonts 101 - 200
	private final short Num_fonts = 101;
	private final short Num_font = 102;
	private final short Num_sz = 103;
	private final short Num_color = 104;
	private final short Num_name = 105;
	private final short Num_family = 106;
	private final short Num_charset = 107;
	private final short Num_scheme = 108;
	private final short Num_b = 109;
	private final short Num_i = 110;
	private final short Num_strike = 111;
	private final short Num_u = 112;
	private final short Num_vertAlign = 113;
	private final short Num_double = 114;
	private final short Num_singleAccounting = 115;
	private final short Num_doubleAccounting = 116;
	private final short Num_superscript = 117;
	// fills 201 - 300
	private final short Num_fills = 201;
	// fill->patternFill
	private final short Num_patternFill = 202;
	// fill->patternFill->fgColor,bgColor
	private final short Num_fgColor = 203;
	private final short Num_bgColor = 204;
	// fill->gradientFill
	private final short Num_gradientFill = 205;
	// fill->gradientFill->stop
	private final short Num_stop = 206;
	// fill->patternFill.patternType:
	private final short Num_gray125 = 207;
	private final short Num_darkGray = 208;
	private final short Num_mediumGray = 209;
	private final short Num_lightGray = 210;
	private final short Num_gray0625 = 211;
	private final short Num_darkHorizontal = 212;
	private final short Num_darkVertical = 213;
	private final short Num_darkDown = 214;
	private final short Num_darkUp = 215;
	private final short Num_darkGrid = 216;
	private final short Num_darkTrellis = 217;
	private final short Num_lightHorizontal = 218;
	private final short Num_lightVertical = 219;
	private final short Num_lightDown = 220;
	private final short Num_lightUp = 221;
	private final short Num_lightGrid = 222;
	private final short Num_lightTrellis = 223;
	private final short Num_solid = 224;
	private final short Num_none = 225;
	private final short Num_path = 226;
	private final short Num_linear = 227;
	// borders 301 - 400
	private final short Num_borders = 301;
	private final short Num_border = 302;
	private final short Num_diagonal = 303;
	private final short Num_hair = 304;
	private final short Num_slantDashDot = 305;
	private final short Num_dotted = 306;
	private final short Num_mediumDashDot = 307;
	private final short Num_dashDotDot = 308;
	private final short Num_doubleL = 309;
	private final short Num_medium = 310;
	private final short Num_mediumDashed = 311;
	private final short Num_dashDot = 312;
	private final short Num_thin = 313;
	private final short Num_dashed = 314;
	private final short Num_thick = 315;
	private final short Num_mediumDashDotDot = 316;
	// cellXfs 401 - 500
	private final short Num_cellXfs = 401;
	private final short Num_xf = 402;
	private final short Num_alignment = 403;
	// cellXfs->alignment->vertical、horizontal
	private final short Num_center = 420;
	private final short Num_justify = 421;
	private final short Num_distributed = 422;
	// cellXfs->alignment->horizontal
	private final short Num_centerContinuous = 423;
	// share short 500 - 550
	// borders,fills,cellXfs->alignment->vertical,
	private final short Num_top = 501;
	private final short Num_bottom = 502;
	// borders,fills,cellXfs->alignment->horizontal
	private final short Num_left = 503;
	private final short Num_right = 504;
	// fills,cellXfs->alignment->horizontal
	private final short Num_fill = 505;

	public NewStylesHandler() {
		stylesDicMap = new HashMap<String, Short>();
		initStylesDic(stylesDicMap);

	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		short qNameIndex;
		if (stylesDicMap.containsKey(qName)) {
			qNameIndex = stylesDicMap.get(qName);
			if (qNameIndex == Num_numFmts) {// numFmts
				styles.setNumFmts(numFmts);
				numFmts = null;
			} else if (qNameIndex == Num_font) {// font
				fonts.add(font);
				font = null;
				startFontTag = false;
			} else if (qNameIndex == Num_fonts) {// fonts
				styles.setFonts(fonts);
				fonts = null;
			} else if (qNameIndex == Num_bgColor) {// bgColor
				fill.setBgColor(colorStyle);
				colorStyle = null;
			} else if (qNameIndex == Num_fgColor) {// fgColor
				fill.setFgColor(colorStyle);
				colorStyle = null;
			} else if (qNameIndex == Num_stop) {// stop
				gFillEffects.addStops(stop);
				stop = null;
				startStopTag = false;
			} else if (qNameIndex == Num_gradientFill) {// gradientFill
				fill.setGFEffects(gFillEffects);
				gFillEffects = null;
			} else if (qNameIndex == Num_fill) {// fill
				fills.add(fill);
				fill = null;
			} else if (qNameIndex == Num_fills) {// fills
				styles.setFills(fills);
				fills = null;
			} else if (qNameIndex == Num_left) {// border->left
				if (bstyle != null) {
					border.setbLeftStyle(bstyle);
				}
				if (colorStyle != null) {
					border.setbLeftColor(colorStyle);
				}
				bstyle = null;
				colorStyle = null;
			} else if (qNameIndex == Num_right) {// border->right
				if (bstyle != null) {
					border.setbRightStyle(bstyle);
				}
				if (colorStyle != null) {
					border.setbRightColor(colorStyle);
				}
				bstyle = null;
				colorStyle = null;
			} else if (qNameIndex == Num_bottom) {// border->bottom
				if (bstyle != null) {
					border.setbBottomStyle(bstyle);
				}
				if (colorStyle != null) {
					border.setbBottomColor(colorStyle);
				}
				bstyle = null;
				colorStyle = null;
			} else if (qNameIndex == Num_top) {// border->top
				if (bstyle != null) {
					border.setbTopStyle(bstyle);
				}
				if (colorStyle != null) {
					border.setbTopColor(colorStyle);
				}
				bstyle = null;
				colorStyle = null;
			} else if (qNameIndex == Num_diagonal) {// border->diagonal
				if (bstyle != null) {
					border.setbDiagonalStyle(bstyle);
				}
				if (colorStyle != null) {
					border.setbDiagonalColor(colorStyle);
				}
				bstyle = null;
				colorStyle = null;
			} else if (qNameIndex == Num_border) {// border
				borders.add(border);
				border = null;
			} else if (qNameIndex == Num_borders) {// borders
				styles.setBorders(borders);
				borders = null;
				startBordersTag = false;
			} else if (startCellXfsTag && qNameIndex == Num_xf) {// cellxfs->xf
				cellXf.setAlignment(alignment);
				alignment = null;
				applyAlignment = false;
				cellXfs.add(cellXf);
				cellXf = null;
			} else if (qNameIndex == Num_cellXfs) {// cellxfs
				styles.setCellxfs(cellXfs);
				cellXfs = null;
				startCellXfsTag = false;
			}
		}
	}

	@Override
	public void startDocument() throws SAXException {

	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		short mapValue;
		if (stylesDicMap.containsKey(qName)) {
			mapValue = stylesDicMap.get(qName);
			switch (mapValue) {
			case Num_numFmts: {// 解析numFmts标签
				numFmts = new HashMap<Integer, String>();
				break;
			}
			case Num_numFmt: {// 解析numFmt标签
				numFmts.put(
						Integer.parseInt(attributes.getValue(QNAME_numFmtId)),
						attributes.getValue(QNAME_formatCode));
				break;
			}
			case Num_fonts: {// fonts
				fonts = new ArrayList<Font>();
				break;
			}
			case Num_font: {// font
				font = new Font();
				startFontTag = true;
				break;
			}
			case Num_sz: {// size
				font.setSize(Byte.parseByte(attributes.getValue(QNAME_val)));
				break;
			}
			case Num_color: {
				if (startFontTag) {// font->color
					colorStyle = parseColorStyle(attributes);
					font.setColor(colorStyle);
					colorStyle = null;
					break;
				} else if (startStopTag) {// stop->color
					colorStyle = parseColorStyle(attributes);
					stop.setColorStyle(colorStyle);
					colorStyle = null;
					break;
				} else if (startBordersTag) {// border->color
					colorStyle = parseColorStyle(attributes);
					break;
				}
			}
			case Num_name: {// name
				font.setName(attributes.getValue(QNAME_val));
				break;
			}
			case Num_family: {// family
				font.setFamily(Short.parseShort(attributes.getValue(QNAME_val)));
				break;
			}
			case Num_charset: {// charset
				font.setCharset(Short.parseShort(attributes.getValue(QNAME_val)));
				break;
			}
			case Num_scheme: {// scheme
				font.setScheme(attributes.getValue(QNAME_val));
				break;
			}
			case Num_b: {// bold
				font.setBold(true);
				break;
			}
			case Num_i: {// incline
				font.setIncline(true);
				break;
			}
			case Num_strike: {// strike
				font.setStrike(true);
				break;
			}
			case Num_u: {// underline
				if ((attriValue = attributes.getValue(QNAME_val)) != null) {
					mapValue = stylesDicMap.get(attriValue);
					if (mapValue == Num_double) {
						font.setU(Font_u.doubleU);
					} else if (mapValue == Num_singleAccounting) {
						font.setU(Font_u.singleAccounting);
					} else if (mapValue == Num_doubleAccounting) {
						font.setU(Font_u.doubleAccounting);
					}
				} else {// default
					font.setU(Font_u.singleU);
				}
				break;
			}
			case Num_vertAlign: {// vertAlign
				attriValue = attributes.getValue(QNAME_val);
				mapValue = stylesDicMap.get(attriValue);
				if (mapValue == Num_superscript) {
					font.setvAlign(VertAlign.Superscript);
				} else {
					font.setvAlign(VertAlign.Subscript);
				}
				break;
			}
			case Num_fills: {// fills
				fills = new ArrayList<Fill>();
				break;
			}
			case Num_fill: {// fill
				fill = new Fill();
				break;
			}
			case Num_patternFill: {// patternFill
				attriValue = attributes.getValue(QNAME_patternType);
				fill.setPatternType(findPatternType(attriValue));
				break;
			}
			case Num_fgColor:// fgColor
			case Num_bgColor: {// bgColor
				colorStyle = parseColorStyle(attributes);
				break;
			}
			case Num_gradientFill: {// gradientFill
				gFillEffects = new GradientFillEffects();
				if ((attriValue = attributes.getValue(QNAME_GradientType)) != null) {// gradientType
					float top = Float.parseFloat(attributes.getValue(SSTR_top));
					float bottom = Float.parseFloat(attributes
							.getValue(SSTR_bottom));
					float left = Float.parseFloat(attributes
							.getValue(SSTR_left));
					float right = Float.parseFloat(attributes
							.getValue(SSTR_right));
					mapValue = stylesDicMap.get(attriValue);
					if (mapValue == Num_path) {
						gFillEffects.setGradientType(GradientType.path, top,
								bottom, left, right);
					} else if (mapValue == Num_linear) {
						gFillEffects.setGradientType(GradientType.linear, top,
								bottom, left, right);
					}
				} else if ((attriValue = attributes.getValue(QNAME_degree)) != null) {// degree
					gFillEffects.setDegree(Short.parseShort(attriValue));
				}
				break;
			}
			case Num_stop: {// stop
				startStopTag = true;
				stop = new Stop();
				float position = Float.parseFloat(attributes
						.getValue(QNAME_position));
				stop.setPosition(position);
				break;
			}
			case Num_borders: {// borders
				borders = new ArrayList<Border>();
				startBordersTag = true;
				break;
			}
			case Num_border: {// border
				border = new Border();
				if (attributes.getValue(QNAME_diagonalUp) != null) {
					border.setDiagonalUp(true);
				}
				if (attributes.getValue(QNAME_diagonalDown) != null) {
					border.setDiagonalDown(true);
				}
				break;
			}
			case Num_left:
			case Num_right:
			case Num_top:
			case Num_bottom:
			case Num_diagonal: {// border->left、right、top、bottom、diagonal
				if ((attriValue = attributes.getValue(QNAME_style)) != null) {
					bstyle = findBorderStyle(attriValue);
				}
				break;
			}
			case Num_cellXfs: {// cellXfs
				cellXfs = new ArrayList<CellXf>();
				startCellXfsTag = true;
				break;
			}
			case Num_xf: {
				if (startCellXfsTag) {// cellXfs->xf
					cellXf = new CellXf();
					alignment = new Alignment();
					if (attributes.getIndex(QNAME_applyNumberFormat) != -1) {// applyNumberFormat
						cellXf.setNumFmtId(Integer.parseInt(attributes
								.getValue(QNAME_numFmtId)));
					}
					if (attributes.getIndex(QNAME_applyBorder) != -1) {// applyBorder
						cellXf.setBorderId(Integer.parseInt(attributes
								.getValue(QNAME_borderId)));
					}
					if (attributes.getIndex(QNAME_applyFill) != -1) {// applyFill
						cellXf.setFillId(Integer.parseInt(attributes
								.getValue(QNAME_fillId)));
					}
					if (attributes.getIndex(QNAME_applyFont) != -1) {// applyFont
						cellXf.setFontId(Integer.parseInt(attributes
								.getValue(QNAME_fontId)));
					}
					if (attributes.getIndex(QNAME_applyAlignment) != -1) {// applyAlignment
						applyAlignment = true;
					}
				}
				break;
			}
			case Num_alignment: {
				if (startCellXfsTag) {// cellXf->alignment
					if (applyAlignment == true) {
						if ((attriValue = attributes.getValue(QNAME_vertical)) != null) {// vertical
							mapValue = stylesDicMap.get(attriValue);
							if (mapValue == Num_center) {
								alignment.setVertical(Vertical.center);
							} else if (mapValue == Num_justify) {
								alignment.setVertical(Vertical.justify);
							} else if (mapValue == Num_distributed) {
								alignment.setVertical(Vertical.distributed);
							} else if (mapValue == Num_top) {
								alignment.setVertical(Vertical.top);
							} else if (mapValue == Num_bottom) {
								alignment.setVertical(Vertical.bottom);
							}
						}
						if ((attriValue = attributes.getValue(QNAME_horizontal)) != null) {// horizontal
							mapValue = stylesDicMap.get(attriValue);
							if (mapValue == Num_center) {
								alignment.setHorizontal(Horizontal.center);
							} else if (mapValue == Num_justify) {
								alignment.setHorizontal(Horizontal.justify);
							} else if (mapValue == Num_distributed) {
								alignment.setHorizontal(Horizontal.distributed);
							} else if (mapValue == Num_left) {
								alignment.setHorizontal(Horizontal.left);
							} else if (mapValue == Num_right) {
								alignment.setHorizontal(Horizontal.right);
							} else if (mapValue == Num_fill) {
								alignment.setHorizontal(Horizontal.fill);
							} else if (mapValue == Num_centerContinuous) {
								alignment
										.setHorizontal(Horizontal.centerContinuous);
							}
						}
						if (attributes.getValue(QNAME_wrapText) != null) {// wrapText
							alignment.setWrapText(true);
						}
						if (attributes.getValue(QNAME_shrinkToFit) != null) {// shrinkToFit
							alignment.setShrinkToFit(true);
						}
						if ((attriValue = attributes
								.getValue(QNAME_readingOrder)) != null) {// readingOrder
							if (attriValue.equals(QVAL_roltr)) {// left to right
								alignment
										.setReadingOrder(ReadingOrder.leftToRight);
							} else if (attriValue.equals(QVAL_rortl)) {// right
																		// to
																		// left
								alignment
										.setReadingOrder(ReadingOrder.RightToLeft);
							}
						}
						if ((attriValue = attributes
								.getValue(QNAME_textRotation)) != null) {// textRotation
							alignment.setTextRotation(Byte
									.parseByte(attriValue));
						}
						if ((attriValue = attributes.getValue(QNAME_indent)) != null) {// indent
							alignment.setIndent(Byte.parseByte(attriValue));
						}
						if (attributes.getValue(QNAME_justifyLastLine) != null) {// justifyLastLine
							alignment.setJustifyLastLine(true);
						}
					}
				}
				break;
			}
			default:
				break;
			}
		}
	}

	private ColorStyle parseColorStyle(Attributes attributes) {
		// parse colorStyle Tag
		ColorStyle colorStyle = new ColorStyle();
		String attriValue;
		if ((attriValue = attributes.getValue(QNAME_theme)) != null) {// theme
			colorStyle.setTheme(Byte.parseByte(attriValue));
			if ((attriValue = attributes.getValue(QNAME_tint)) != null) {// tint
				colorStyle.setTint(Double.parseDouble(attriValue));
			}
		} else if ((attriValue = attributes.getValue(QNAME_rgb)) != null) {// rgb
			colorStyle.setRgb(SheetTools.rgbStringToInt(attriValue));
		} else if ((attriValue = attributes.getValue(QNAME_indexed)) != null) {// indexed
			colorStyle.setIndexed(Byte.parseByte(attriValue));
		} else if (attributes.getValue(QNAME_auto) != null) {// auto
			colorStyle.setTheme((byte) 1);
		}
		return colorStyle;
	}

	private PatternType findPatternType(String val) {
		short numVal;
		if (stylesDicMap.containsKey(val)) {
			numVal = stylesDicMap.get(val);
		} else
			return null;

		switch (numVal) {
		case Num_none:
			return PatternType.none;
		case Num_gray125:
			return PatternType.gray125;
		case Num_darkGray:
			return PatternType.darkGray;
		case Num_mediumGray:
			return PatternType.mediumGray;
		case Num_lightGray:
			return PatternType.lightGray;
		case Num_gray0625:
			return PatternType.gray0625;
		case Num_darkHorizontal:
			return PatternType.darkHorizontal;
		case Num_darkVertical:
			return PatternType.darkVertical;
		case Num_darkDown:
			return PatternType.darkDown;
		case Num_darkUp:
			return PatternType.darkUp;
		case Num_darkGrid:
			return PatternType.darkGrid;
		case Num_darkTrellis:
			return PatternType.darkTrellis;
		case Num_lightHorizontal:
			return PatternType.lightHorizontal;
		case Num_lightVertical:
			return PatternType.lightVertical;
		case Num_lightDown:
			return PatternType.lightDown;
		case Num_lightUp:
			return PatternType.lightUp;
		case Num_lightGrid:
			return PatternType.lightGrid;
		case Num_lightTrellis:
			return PatternType.lightTrellis;
		case Num_solid:
			return PatternType.solid;
		default:
			return null;
		}
	}

	private BorderStyle findBorderStyle(String val) {
		short mapVal;
		if (stylesDicMap.containsKey(val)) {
			mapVal = stylesDicMap.get(val);
		} else
			return null;

		switch (mapVal) {
		case Num_hair:
			return BorderStyle.hair;
		case Num_slantDashDot:
			return BorderStyle.slantDashDot;
		case Num_dotted:
			return BorderStyle.dotted;
		case Num_mediumDashDot:
			return BorderStyle.mediumDashDot;
		case Num_dashDotDot:
			return BorderStyle.dashDotDot;
		case Num_doubleL:
			return BorderStyle.doubleL;
		case Num_medium:
			return BorderStyle.medium;
		case Num_mediumDashed:
			return BorderStyle.mediumDashed;
		case Num_dashDot:
			return BorderStyle.dashDot;
		case Num_dashed:
			return BorderStyle.dashed;
		case Num_thick:
			return BorderStyle.thick;
		case Num_mediumDashDotDot:
			return BorderStyle.mediumDashDotDot;
		default:
			return null;
		}
	}

	private void initStylesDic(Map<String, Short> map) {
		// numFmts
		map.put(TAG_numFmts, Num_numFmts);
		map.put(TAG_numFmt, Num_numFmt);
		map.put(QNAME_formatCode, Num_formatCode);

		// fonts
		map.put(TAG_fonts, Num_fonts);
		map.put(TAG_font, Num_font);
		map.put(TAG_sz, Num_sz);
		map.put(TAG_color, Num_color);
		map.put(TAG_name, Num_name);
		map.put(TAG_family, Num_family);
		map.put(TAG_charset, Num_charset);
		map.put(TAG_scheme, Num_scheme);
		map.put(TAG_b, Num_b);
		map.put(TAG_i, Num_i);
		map.put(TAG_strike, Num_strike);
		map.put(TAG_u, Num_u);
		map.put(TAG_vertAlign, Num_vertAlign);
		map.put(QVAL_double, Num_double);
		map.put(QVAL_singleAccounting, Num_singleAccounting);
		map.put(QVAL_doubleAccounting, Num_doubleAccounting);
		map.put(QVAL_superscript, Num_superscript);
		// fills
		map.put(TAG_fills, Num_fills);
		// fill->patternFill
		map.put(TAG_patternFill, Num_patternFill);
		// fill->patternFill->fgColor,bgColor
		map.put(TAG_fgColor, Num_fgColor);
		map.put(TAG_bgColor, Num_bgColor);
		// fill->gradientFill
		map.put(TAG_gradientFill, Num_gradientFill);
		// fill->gradientFill->stop
		map.put(TAG_stop, Num_stop);
		// fill->patternFill.patternType:
		map.put(QVAL_gray125, Num_gray125);
		map.put(QVAL_darkGray, Num_darkGray);
		map.put(QVAL_mediumGray, Num_mediumGray);
		map.put(QVAL_lightGray, Num_lightGray);
		map.put(QVAL_gray0625, Num_gray0625);
		map.put(QVAL_darkHorizontal, Num_darkHorizontal);
		map.put(QVAL_darkVertical, Num_darkVertical);
		map.put(QVAL_darkDown, Num_darkDown);
		map.put(QVAL_darkUp, Num_darkUp);
		map.put(QVAL_darkGrid, Num_darkGrid);
		map.put(QVAL_darkTrellis, Num_darkTrellis);
		map.put(QVAL_lightHorizontal, Num_lightHorizontal);
		map.put(QVAL_lightVertical, Num_lightVertical);
		map.put(QVAL_lightDown, Num_lightDown);
		map.put(QVAL_lightUp, Num_lightUp);
		map.put(QVAL_lightGrid, Num_lightGrid);
		map.put(QVAL_lightTrellis, Num_lightTrellis);
		map.put(QVAL_solid, Num_solid);
		map.put(QVAL_none, Num_none);
		map.put(QVAL_path, Num_path);
		map.put(QVAL_linear, Num_linear);
		// borders
		map.put(TAG_borders, Num_borders);
		map.put(TAG_border, Num_border);
		map.put(TAG_diagonal, Num_diagonal);
		map.put(QVAL_hair, Num_hair);
		map.put(QVAL_slantDashDot, Num_slantDashDot);
		map.put(QVAL_dotted, Num_dotted);
		map.put(QVAL_mediumDashDot, Num_mediumDashDot);
		map.put(QVAL_dashDotDot, Num_dashDotDot);
		map.put(QVAL_doubleL, Num_doubleL);
		map.put(QVAL_medium, Num_medium);
		map.put(QVAL_mediumDashed, Num_mediumDashed);
		map.put(QVAL_dashDot, Num_dashDot);
		map.put(QVAL_thin, Num_thin);
		map.put(QVAL_dashed, Num_dashed);
		map.put(QVAL_thick, Num_thick);
		map.put(QVAL_mediumDashDotDot, Num_mediumDashDotDot);
		// cellXfs
		map.put(TAG_cellXfs, Num_cellXfs);
		map.put(TAG_xf, Num_xf);
		map.put(TAG_alignment, Num_alignment);

		// cellXfs->alignment->vertical、horizontal
		map.put(QVAL_center, Num_center);
		map.put(QVAL_justify, Num_justify);
		map.put(QVAL_distributed, Num_distributed);
		// cellXfs->alignment->horizontal
		map.put(QVAL_centerContinuous, Num_centerContinuous);
		// share string -------------------
		// borders,fills,cellXfs->alignment->vertical,
		map.put(SSTR_top, Num_top);
		map.put(SSTR_bottom, Num_bottom);
		// borders,fills,cellXfs->alignment->horizontal
		map.put(SSTR_left, Num_left);
		map.put(SSTR_right, Num_right);
		// fills,cellXfs->alignment->horizontal
		map.put(SSTR_fill, Num_fill);
		// ---------------------------------
	}
}
